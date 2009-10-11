package confdb.gui;

import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;

import confdb.data.EventContent;
import confdb.data.Stream;
import confdb.data.Configuration;

/**
 * CreateStreamDialog
 * ------------------
 * @author Philipp Schieferdecker
 *
 * Let the user create a new stream and either link it
 */
public class CreateStreamDialog extends javax.swing.JPanel
{
    //
    // member data
    //
    
    /** reference to the configuration */
    private Configuration config = null;

    /** created stream */
    private Stream stream = null;


    //
    // construction
    //
    
    /** Creates new form CreateStreamDialog */
    public CreateStreamDialog(Configuration config)
    {
        this.config = config;
        initComponents();
        updateEventContentList();
    }

    //
    // public member functions
    //

    /** indicate if a stream was successfully created */
    public boolean isSuccess() { return stream!=null; }

    /** retrieve the created stream */
    public Stream stream() { return stream; }


    //
    // private member functions
    //

    /** update the list of available event context, put into combo box */
    private void updateEventContentList()
    {
        DefaultComboBoxModel cbm =
           (DefaultComboBoxModel)jComboBoxEventContent.getModel();
        cbm.removeAllElements();
        cbm.addElement(new String("<NEW>"));
        Iterator<EventContent> itC = config.contentIterator();
        while (itC.hasNext()) {
            EventContent content = itC.next();
            cbm.addElement(content.label());
        }
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldStreamLabel = new javax.swing.JTextField();
        jLabelStreamLabel = new javax.swing.JLabel();
        jLabelEventContent = new javax.swing.JLabel();
        jComboBoxEventContent = new javax.swing.JComboBox();
        jButtonOK = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();

        jTextFieldStreamLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldStreamLabelActionPerformed(evt);
            }
        });

        jLabelStreamLabel.setText("Stream Name:");

        jLabelEventContent.setText("Event Content:");

        jComboBoxEventContent.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButtonOK.setText("OK");
        jButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOKActionPerformed(evt);
            }
        });

        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldStreamLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabelStreamLabel)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxEventContent, 0, 377, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabelEventContent))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(589, Short.MAX_VALUE)
                        .addComponent(jButtonCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonOK)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonCancel, jButtonOK});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelStreamLabel)
                    .addComponent(jLabelEventContent))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldStreamLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxEventContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonOK)
                    .addComponent(jButtonCancel))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonOKActionPerformed
    {//GEN-HEADEREND:event_jButtonOKActionPerformed
        String streamLabel = jTextFieldStreamLabel.getText();
        String contentLabel = (String)jComboBoxEventContent.getSelectedItem();
        EventContent content = config.content(contentLabel);
        if (content==null) {
            content = config.insertContent(config.contentCount(),
                                           "hltEventContent" + streamLabel);
        }
        stream = content.insertStream(streamLabel);
        setVisible(false);
    }//GEN-LAST:event_jButtonOKActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonCancelActionPerformed
    {//GEN-HEADEREND:event_jButtonCancelActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButtonCancelActionPerformed

    private void jTextFieldStreamLabelActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jTextFieldStreamLabelActionPerformed
    {//GEN-HEADEREND:event_jTextFieldStreamLabelActionPerformed
        String streamLabel = jTextFieldStreamLabel.getText();
        if (config.stream(streamLabel)==null) jButtonOK.setEnabled(false);
        else jButtonOK.setEnabled(true);
    }//GEN-LAST:event_jTextFieldStreamLabelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonOK;
    private javax.swing.JComboBox jComboBoxEventContent;
    private javax.swing.JLabel jLabelEventContent;
    private javax.swing.JLabel jLabelStreamLabel;
    private javax.swing.JTextField jTextFieldStreamLabel;
    // End of variables declaration//GEN-END:variables

}
