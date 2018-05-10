
/**
* The purpose of this class is to run the swing interface
* @author Daniel McNamara 14024780
* @version 1.0
*/

public class ControllerGUI {

	public static void main(String[] args){
		
		 javax.swing.SwingUtilities.invokeLater(new Runnable() {
			 public void run() {
			 MainForm f = new MainForm();
			 f.setSize(600,550);
			 f.setVisible(true);
			 }
			 });
	}
	
}
