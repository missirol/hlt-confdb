Recipe to update python dependencies of ConfDB
  - needed mainly to parse python configuration into the database
  - recipe requires access to `/cvmfs/cms.cern.ch`

```bash
cd hlt-confdb/python

## IMPORTANT NOTE:
##  When the python libraries in ConfDB have to be updated based on a newer CMSSW release,
##  it is not sufficient to just update the release version in ${cmssw_rel_base},
##  because ad-hoc changes to FWCore/ParameterSet (see below)
##  are needed to make the python parser of ConfDB work.
##  When it is necessary to update these CMSSW python libraries in ConfDB,
##  please check that those ad-hoc changes still apply (if not, remove them, or update them).

cmssw_rel_base=/cvmfs/cms.cern.ch/el8_amd64_gcc11/cms/cmssw/CMSSW_13_0_1

# note: for these packages, CMSSW_13_0_1 and CMSSW_13_1_0_pre2 are equivalent
cmssw_pkgs=(
  FWCore/ParameterSet
  HeterogeneousCore/Common
  HeterogeneousCore/AlpakaCore
  HeterogeneousCore/CUDACore
  HeterogeneousCore/ROCmCore
)

for pkg in "${cmssw_pkgs[@]}"; do
  pkg_py="${cmssw_rel_base}"/src/"${pkg}"/python
  [ -d "${pkg_py}" ] || continue
  rm -rf ${pkg} \
    && cp -r "${pkg_py}" "${pkg}" \
    && touch "${pkg}"/{.,..}/__init__.py
done
unset cmssw_rel_base cmssw_pkgs pkg

# ad-hoc fix to Modules.py for ConfDB's python parser
[ ! -f FWCore/ParameterSet/Modules.py ] || \
sed -e 's|super()|super(SwitchProducer,self)|g' \
    -i FWCore/ParameterSet/Modules.py

# ad-hoc fix to OrderedSet.py for ConfDB's python parser
[ ! -f FWCore/ParameterSet/OrderedSet.py ] || \
sed -e 's|import collections.abc|import collections as collections_abc|g' \
    -e 's|collections.abc.MutableSet|collections_abc.MutableSet|g' \
    -i FWCore/ParameterSet/OrderedSet.py

# package: enum (source: https://pypi.org/project/enum34)
# note: required by HeterogeneousCore/Common/python/PlatformStatus.py
wget https://files.pythonhosted.org/packages/11/c4/2da1f4952ba476677a42f25cd32ab8aaf0e1c0d0e00b89822b835c7e654c/enum34-1.1.10.tar.gz
tar xzf enum34-1.1.10.tar.gz
rm -rf enum && mv enum34-1.1.10/enum .
rm -rf enum34-1.1.10*
```
