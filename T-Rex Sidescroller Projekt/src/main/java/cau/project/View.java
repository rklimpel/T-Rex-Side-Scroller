package main.java.cau.project;

/**
 * The main-View-class which creates the Update and exit Method, which are overwritten in End- and Game-View
 */
public class View {
   //creation of the classvariables
   public View view;

   private String viewID;

   /**
    * The Update-method, which is overwritten in the LhView, DesktopView, and GameView
    */
   public void Update() {

   }

   /**
    * Overwritten-method for showing he next View
    * @param nextViewID viewID for the next shown view
    */
   public void exit(String nextViewID) {

   }

   /**
    * possibility for other classes to get private viewID
    * @return ViewID
    */
   public String getViewID() {
      return viewID;
   }

   /**
    * possibility for other classes to set private viewID
    * @param viewID
    */
   public void setViewID(String viewID) {
      this.viewID = viewID;
   }
}
