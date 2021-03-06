package bibliotheque;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import recherche.*;

/**
 *
 * @author Hamza Hamidi, Nguyen Tien Manh, Nguyen Tien Dung
 */
public class MainWindow extends javax.swing.JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Creates new form MainWindow
     */
    public MainWindow() {
    	similar_books = new Hashtable<Integer, String[]>();
    	content = new Hashtable<Integer, ArrayList<String>>();
		currentLocale = Locale.getDefault();
		initComponents();
    }
    /**
     *  Init all graphical components and all their listeners:
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    	
    	getContentPane().removeAll();

    	// create locale
        labels = ResourceBundle.getBundle("resources.Resources", currentLocale);


        button1 = new java.awt.Button();
        label1 = new java.awt.Label();
        textField1 = new JTextField();
        textField2 = new java.awt.TextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        MyTableModel model = new MyTableModel(currentLocale);

        sorter = new TableRowSorter<MyTableModel>(model);
        jTable1 = new JTable(model);
        jTable1.setRowSorter(sorter);
        //jTable1.setFillsViewportHeight(true);

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        
        
        
        
        jMenuItem9.setText(labels.getString("Import_PDF"));
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
      
        });
        jPopupMenu1.add(jMenuItem9);

        jMenuItem10.setText(labels.getString("Open_PDF"));
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }

        });
        jPopupMenu1.add(jMenuItem10);

        jMenuItem11.setText(labels.getString("Delete_PDF"));
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
         });
        jPopupMenu1.add(jMenuItem11);

        jMenuItem12.setText(labels.getString("Delete_element"));
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem12);
        
        getContentPane().add(jPopupMenu1);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridBagLayout());
        
        //button1.setLabel("Search");
        button1.setLabel(labels.getString("Search"));
        button1.setMaximumSize(new java.awt.Dimension(55, 24));
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        

        label1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        label1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        label1.setText(labels.getString("Similar_book"));
        
        jTable1.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseReleased(MouseEvent evt) {
    			if(evt.isPopupTrigger())  {
                    JTable source = (JTable)evt.getSource();
                    li = source.rowAtPoint( evt.getPoint() );
                    System.out.println(li);

            jPopupMenu1.show(evt.getComponent(),evt.getX(),evt.getY());

    			}
    		}
    		});
        
        
        // Event when we click to a row in table:
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
            	textField2.setText("");
            	if(jTable1.getSelectedRow() >=0) {
            		String title_select = (String) jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 2);
            		int id_found = tag_titre.Booksearch(title_select);
            		// Display the similar books
            		for(int i = 0; i < 3 ;i++){
            		textField2.append(similar_books.get(id_found)[i].toString()+ "\n");
            		}
            	}
            	else{
            		return;
            	}
            }
        });
        // Save the collection data after user hit enter:
        jTable1.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					
					String title_select = (String) jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 2);
	            	int id_found = tag_titre.Booksearch(title_select);
	            	// Save the data from it:
	            	ArrayList<String> content_temp = content.get(id_found);
	            	content.remove(id_found);
	            	content_temp.set(0, (String) jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 0));
	            	content_temp.set(13, (String) jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 10));
	            	content.put(id_found, content_temp);
				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        textField1.getDocument().addDocumentListener(
                new DocumentListener() {
                    public void changedUpdate(DocumentEvent e) {
                        newFilter();
                    }
                    public void insertUpdate(DocumentEvent e) {
                        newFilter();
                    }
                    public void removeUpdate(DocumentEvent e) {
                        newFilter();
                    }
                });
        // Event when we lost focus a row in the table:
        jScrollPane1.setViewportView(jTable1);
       
        // TRICK : Add empty gridbaglayout components to scaling the interface, for the vertical line:
        for(int i = 0; i < 30; i++){
        	getContentPane().add(new JLabel(""), new GridBagConstraints(i, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
        }
        
        // TRICK: Add empty gridbaglayout components to scaling the interface, for the horizontal line:
        for(int i = 0; i < 40; i++){
        	getContentPane().add(new JLabel(""), new GridBagConstraints(1, i, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
        }
        // Set all the items to the panel:
        getContentPane().add(textField1, new GridBagConstraints(27, 5, 2, 1, 1.0, 1.0, GridBagConstraints.NORTHEAST, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
        getContentPane().add(button1, new GridBagConstraints(29, 5, 1, 1, 1.0, 1.0, GridBagConstraints.NORTHEAST, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
        getContentPane().add(jScrollPane1, new GridBagConstraints(0, 10 , 30, 30, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
        getContentPane().add(label1, new GridBagConstraints(24, 40, 1, 2, 1.0, 1.0, GridBagConstraints.SOUTHEAST, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
        getContentPane().add(textField2, new GridBagConstraints(25, 40, 5, 2, 1.0, 1.0, GridBagConstraints.SOUTHEAST, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
       

        // Create menu system
        jMenu1.setText(labels.getString("File"));
        jMenu1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jMenu1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jMenu1FocusGained(evt);
            }
        });

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText(labels.getString("Import"));
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);


        jMenuBar1.add(jMenu1);

        jMenu2.setText(labels.getString("Edit"));
        
        jMenuItem3.setText(labels.getString("Delete_Collection"));
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);
        
        jMenuBar1.add(jMenu2);

        jMenu5.setText(labels.getString("About"));

        jMenuItem5.setText("By INSA CVL 2017 COPYRIGHT");
        jMenu5.add(jMenuItem5);
        
        jMenu3.setText(labels.getString("Language"));
        
        jMenuItem7.setText(labels.getString("English"));
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        
        jMenuItem8.setText(labels.getString("French"));
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        
        jMenu3.add(jMenuItem7);
        jMenu3.add(jMenuItem8);
        
        	
        jMenuBar1.add(jMenu5);
        
        jMenuBar1.add(jMenu3);
        setJMenuBar(jMenuBar1);
        
       
        //setResizable(false);
        //setVisible(true);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
    	// Take text from textField2:
    	newFilter();
    }//GEN-LAST:event_button1ActionPerformed
    
    
    private void jMenu1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jMenu1FocusGained
        // TODO add your handling code here:
    	
    }//GEN-LAST:event_jMenu1FocusGained

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
    	 JTextField textArea = new JTextField();
         textArea.setEditable(true);
         JScrollPane scrollPane = new JScrollPane(textArea);
         scrollPane.requestFocus();
         textArea.requestFocusInWindow();
         scrollPane.setPreferredSize(new Dimension(40, 50));
         JOptionPane.showMessageDialog(this, scrollPane,
                 labels.getString("Message_Search"), JOptionPane.PLAIN_MESSAGE);
         int number_row_removed = 0;
         String info = textArea.getText();
         // Remove selection mode:

         for(int i = id -1; i >=0; i--) {
         	String collection = (String) jTable1.getModel().getValueAt(i, 0);
         	if(collection.equals(info)) {
         		// Remove the data in jTable
        		MyTableModel model = (MyTableModel) jTable1.getModel();
        		model.removeRow(i);
         		// Remove the data in the content ( to remove in XML)
         		content.remove(i);
         		number_row_removed ++;
     		}
 		}
        id -= number_row_removed; 
         // Search all the data in the content 
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
    	String[] infoo = new String [12]; 
        JFileChooser chooser= new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("BIBTEX FILES", "bib", "text");
		chooser.setFileFilter(filter);
		chooser.showOpenDialog(null);
		File f= chooser.getSelectedFile();
		String filename= f.getAbsolutePath();
		filenamee[li]= f.getName();

		//System.out.println(filename);
		        try {
		            Parser parser = new Parser(filename);
		                    infoo=parser.getinfo();

		        } catch (FileNotFoundException ex) {
		            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
		        }
		MyTableModel model = (MyTableModel) jTable1.getModel();
		model.insertRow(id , new Object[]{"",infoo[0], infoo[1], infoo[2], infoo[3], infoo[4], infoo[5], infoo[7], "", infoo[11], "", false});
        model.fireTableRowsInserted(0, id );

        tag_titre.addTag(infoo[1], id);
        
        ArrayList<String> array_content = new ArrayList<String>();
        array_content.add(""); // Collection
        array_content.add(infoo[0]); // Author
        array_content.add(infoo[1]); // Title
        array_content.add(infoo[2]); //journal
        array_content.add(infoo[3]); // year
        array_content.add(infoo[4]); //volume
        array_content.add(infoo[5]); // number
        array_content.add(infoo[6]);  // pages
        array_content.add(infoo[7]); // month
        array_content.add(infoo[8]); // doi
        array_content.add(infoo[9]); //url
        array_content.add(infoo[10]); //Abstract
        array_content.add(infoo[11]); // keywords
        //array_content.add(infoo[12]); //pdf
        array_content.add(""); //tag

        content.put(id, array_content);
        // Search for the similar books:
        String info_titre = infoo[1];
        // Create a requete
        Requete newRequest = new Requete();
        
        // 3 String arrays used to get each title and date of suggested article:
        String afficheResult[] = new String[3];
        // get suggested article:
        afficheResult = newRequest.finalResult(info_titre);
        // ADdd these 3 articles to the hashTable to display later:
        similar_books.put(id, afficheResult);
        // Increment the id :
        id++;

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
    	Locale currentLocale1 =  new Locale.Builder().setLanguage("en")
                .setRegion("EN").build();
    	currentLocale = currentLocale1;
    	initComponents();
    }//GEN-LAST:event_jMenuItem7ActionPerformed
    
    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
    	Locale currentLocale1 =  new Locale.Builder().setLanguage("fr")
                .setRegion("FR").build();
    	currentLocale = currentLocale1;
    	initComponents();
    }//GEN-LAST:event_jMenuItem8ActionPerformed
    
    private void jMenuItem9ActionPerformed(ActionEvent evt) {
    	
    	JFileChooser chooser= new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF FILES", "pdf", "text");
        chooser.setFileFilter(filter);
        chooser.showOpenDialog(null);
        File f= chooser.getSelectedFile();
        filename1[li]= f.getAbsolutePath();
        filenamee[li]=f.getName();
        if(!filename1[li].equals("")){
        JOptionPane.showMessageDialog(null, filenamee[li]+ labels.getString("Import_PDF_status"));
        jTable1.setValueAt(true, li, 11);
        }
    	
    }
	private void jMenuItem10ActionPerformed(ActionEvent evt) {
		
		
		String s =new String();
		s=filename1[li];
		System.out.println(s);
		        if (s.equals("")){
		        JOptionPane.showMessageDialog(null, labels.getString("Open_PDF_stauts"));
		        }else{
		        	try {
		                File myFile = new File( s);
		                Desktop.getDesktop().open(myFile);
		            	} catch (IOException ex) {
		                // no application registered for PDFs
		            	}
		       }
	}
	private void jMenuItem11ActionPerformed(ActionEvent evt) {
		
		
		filename1[li]="";
        jTable1.setValueAt(false, li, 11);
		JOptionPane.showMessageDialog(null, labels.getString("Delete_PDF_status"));
	}
	private void jMenuItem12ActionPerformed(ActionEvent evt) {
		
		DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
		model.removeRow(li);
		filename1[li]="";
		JOptionPane.showMessageDialog(null, labels.getString("Delete_element_status"));
	}
	
    /** 
     * Update the row filter regular expression from the expression in
     * the text box.
     */
    private void newFilter() {
        RowFilter<MyTableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(textField1.getText(), 10);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }


    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
		for(int i=0;i<300;i++){
			filename1[i] ="";
			filenamee[i] ="";}
		
	

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button button1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu3;

    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private java.awt.Label label1;
    private JTextField textField1;
    private java.awt.TextArea textField2;
    // End of variables declaration//GEN-END:variables
    public Locale currentLocale = new Locale.Builder().setLanguage("en").setRegion("EN").build();

    private static ResourceBundle labels;
    
	private javax.swing.JMenuItem jMenuItem9;
	private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JPopupMenu jPopupMenu1;
    static String []filename1= new String[300];
	static String []filenamee= new String[300];
	private Hashtable<Integer, String[]> similar_books;
	private title_id tag_titre = new title_id();
	private int id = 0;
	private Hashtable<Integer, ArrayList<String>> content;
    private TableRowSorter<MyTableModel> sorter;

	int li=0;
	
}
