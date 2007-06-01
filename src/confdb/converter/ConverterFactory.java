package confdb.converter;

import java.util.HashMap;

import confdb.converter.html.HtmlConfigurationWriter;
import confdb.converter.html.HtmlEDSourceWriter;
import confdb.converter.html.HtmlESSourceWriter;
import confdb.converter.html.HtmlModuleWriter;
import confdb.converter.html.HtmlParameterWriter;
import confdb.converter.html.HtmlPathWriter;
import confdb.converter.html.HtmlServiceWriter;


public class ConverterFactory {

	static private HashMap<String, String> cmssw2version = null;
	static private final String thisPackage = ConverterFactory.class.getPackage().getName();

	private String version = null;
	private String writerPackage = "";
	private String outputFormat = "";
	
	static public ConverterFactory getFactory( String releaseTag )
	{
		if ( cmssw2version == null )
		{
			cmssw2version = new HashMap<String, String>();
			cmssw2version.put("default", "" );			
		}
		return new ConverterFactory( releaseTag );
	}
	

	static public ConverterFactory getFactory()
	{
		if ( cmssw2version == null )
		{
			cmssw2version = new HashMap<String, String>();
			cmssw2version.put("default", "" );			
		}
		return new ConverterFactory( null );
	}
	

	public Converter getConverter( String typeOfConverter ) throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		char[] type = typeOfConverter.toLowerCase().toCharArray();
		type[0] = Character.toUpperCase( type[0] );
		outputFormat = new String( type );
		if ( !outputFormat.equals( "Ascii") && !outputFormat.equals( "Html") ) 
			return null;
		
		writerPackage = thisPackage + "." + outputFormat.toLowerCase();
		return getConverter();
	}
	
	public Converter getConverter() throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		Converter converter = new Converter();
		converter.setConfigurationWriter( getConfigurationWriter() );
		converter.setParameterWriter( getParameterWriter() );

		converter.setEDSourceWriter( getEDSourceWriter() );
		converter.setESSourceWriter( getESSourceWriter() );
		converter.setServiceWriter( getServiceWriter() );
		converter.setModuleWriter( getModuleWriter() );
		converter.setPathWriter( getPathWriter() );
		converter.setSequenceWriter( getSequenceWriter() );
		
		return converter;
	}

	public IConfigurationWriter getConfigurationWriter() 
	  throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		return (IConfigurationWriter)getWriter( "ConfigurationWriter" );
	}

	public IPathWriter getPathWriter() 
	  throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		return (IPathWriter)getWriter( "PathWriter" );
	}

	
	public IServiceWriter getServiceWriter() 
	  throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		return (IServiceWriter)getWriter( "ServiceWriter" );
	}
	
	public IParameterWriter getParameterWriter() 
	  throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		return (IParameterWriter)getWriter( "ParameterWriter" );
	}

	public IEDSourceWriter getEDSourceWriter()
	  throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		return (IEDSourceWriter)getWriter( "EDSourceWriter" );
	}

	public IESSourceWriter getESSourceWriter()
	  throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		return (IESSourceWriter)getWriter( "ESSourceWriter" );
	}

	public IModuleWriter getModuleWriter()
	  throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		return (IModuleWriter)getWriter( "ModuleWriter" );
	}
	
	public ISequenceWriter getSequenceWriter()
	  throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		return (ISequenceWriter)getWriter( "SequenceWriter" );
	}

	
	private ConverterFactory( String releaseTag )
	{
		version = cmssw2version.get( releaseTag );
		if ( version == null )
			version = cmssw2version.get( "default" );
	}
	
	private Object getWriter( String type ) throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		String className = writerPackage + "." + outputFormat + type + version;
		Class c = Class.forName( className );
		return c.newInstance();
	}

	
}
