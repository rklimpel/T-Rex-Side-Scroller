package main.java.cau.project;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.java.cau.project.screens.game.controller.GameController;
import main.java.cau.project.services.LighthouseService;
import main.java.cau.project.services.SceneSwitcher;

/**
 *
 * Der Kommentar ist sonst auch gut in der mit anliegenden Textdatei zu lesen ;-)
 *
 * Über unser MVC Modell und die Klassenstruktur: Am Anfang sind wir die Sache sogar etwas falsch angegangen und wir
 * hatten ein Modell, das die Daten im Hintergrund berechnet und immer einen View und einen dazugehörigen Controller je
 * Ansicht des Spiels. Wir haben das Ganze dann noch einmal umstrukturiert so dass es jetzt folgendermaßen aussieht.
 * Alle unsere Views gehören der Superklasse „View“ an, in der für Views verallgemeinerte Methoden und Variablen
 * gespeichert sind. So hat z.B. jeder unserer Views eine viewID, mit der wir eindeutig identifizieren können was wir
 * gerade sehen und was wir damit machen können. Die beiden GameViews (LhView & DesktopView) gehören der Superklasse
 * GameView an. Diese hält wiederum die der für GameViews verallgemeinerte Methoden und Variablen: z.B. Berechnung von
 * FPS,  das Verlassen des Spielbildschirms hin zum EndView (Gameover Bildschrim) oder auch das setzen eines Spiel
 * Controllers falls noch keiner vorhanden ist. Unsere Views sind von allem anderen getrennt und haben eine Update
 * Methode die in bestimmten Zeitabständen vom Controller aufgerufen wird. Wenn die Views sich updaten beziehen sie über
 * den Controller die neusten Daten aus dem Gamemodel und zeigen diese individuell an. Der Controller ist so viel wie
 * der Vermittler zwischen den ausführenden Views und dem berechnenden Gamemodel. Es gibt nur einen Controller. Wenn ein
 * neuer View gestartet wird guckt dieser View ob unser Programm schon einen aktiven Controller hat. Wenn nicht, dann
 * wir eine neue Instanz eines Controllers erstellt. Falls es schon einen Controller gibt ruft unser View die addView
 * Methode des Controllers auf und fügt sich so zu einer ArrayListe von Views im Controller hinzu. Der Controller ruft
 * bei allen Views die in dieser Liste liegen die Update Methode auf. So ist es bei uns möglich das auch wenn das Spiel
 * schon läuft ein View zu einem späteren Zeitpunkt einsteigen kann. Beim Erstellen eines Views wird außerdem ein
 * Keyboard und Mouse Listener erstellt. Dies sind bei uns extra Klassen, die auf das Hauptfenster einen Listener
 * setzen. In den Keyboard und/oder MouseListeners ist die Information über den View gespeichert der sie aufgerufen hat.
 * So können wir in den Listenern unterscheiden ob wir uns z.B. im Menu befinden und mit einem Druck auf Enter einfach
 * nur ein Spiel starten müssen, oder ob wir uns im Spiel befinden und durch einen Druck auf die Leertaste den
 * Controller über einen Spieltastendruck informieren müssen. Der Controller nimmt diesen Spieltastendruck auf. Wenn man
 * z.B. die Leertaste drückt, oder die linke Maustaste wird immer im Controller eine Methode aufgerufen das die
 * „Sprungtaste“ gedrückt wurde. Der Controller sieht dann kurz nach ob das Spiel schon läuft oder nicht. Wenn es nicht
 * läuft startet er im Gamemodel das Spiel. Wenn es schon läuft ruft er im Gamemodel den Sprung auf. Und wenn das Spiel
 * schon einen Gameover Status hat dann sagt er all seinen zuhörenden Views Bescheid das sie sich schließen und einen
 * EndView anzeigen sollen. Die letzte und umfangreichste Komponente unseres MVC Modells ist das Modell. Es ist die
 * Klasse die mit der Hilfe einer Menge weiterer Klassen die Gesamten Spielberechnungen vornimmt. Hier wird ein
 * dauerhaft laufender Gametimer gestartet der nach und nach die Levels lädt, Spielobjekte verschiebt auf Kollisionen
 * überprüft und so weiter. Außerdem kommen hier die vom Gamecontroller weitergeleiteten Nutzereingaben an. Um beim
 * Sprungbeispiel zu bleiben wissen wir nun hier bereit nur noch das beim Druck auf die Leertaste ein Sprung ausgeführt
 * werden soll. Es wird kurz geguckt ob das Möglich ist oder nicht und wenn ja das wenn der Player Klasse mitgeteilt das
 * sie springen soll. Alle Spielobjekte mit denen unser Modell arbeitet sind eigene Klassen. Fast alle gehören der
 * Superclass GameObject an, in der sich Variablen wie die Position oder die Farbe des GameObjects zusammenfassen. Im
 * Gamemodell gibt es dann wieder ArrayListen in denen die Instanzen dieser Objekte gespeichert sind. Wir haben also
 * z.B. eine Liste mit Obstacles, eine mit Platformen, einen mit Powerups und so weiter. Die Daten die in diesen
 * Objekten gespeichert sind können sich die Views dann aus dem Gamemodel abgreifen und anzeigen. Um noch einmal
 * explizit auf unsere Klassenstruktur einzugehen, lässt sich sagen das wir zwei große Strukturen haben. Einmal die
 * Views, die mit der Superklasse View deren direkte Kinder das Menu und der Endbildschirm sind. Und darunter gibt es
 * dann noch unter der Superklasse GameView den Desktop und den Lighthouse View. Diese Struktur ist so gewählt damit
 * keine Variablen oder Methoden doppelt geschrieben werden müssen. Außerdem kann man so an den Keyboard-oder
 * MouseListener einfach nur einen View übergeben und dann anhand der ViewID herausfinden was für ein View ist es. Die
 * zweite größere Klassenstruktur die wir haben ist die unter der Superklasse Gameobjekt. Hier gibt es keine weiteren
 * Verzweigungen. Es gibt nur viele Unterklassen wie die Hindernisse, die Powerups, die Plattformen usw. In der
 * Superklasse Gameobjekt ist z.B. die Kollisionsmethode für Spielobjekte, und die Positionen. Halt wieder die Dinge die
 * alle Objekte im Spiel benötigen. Alle anderen Klassen gehören nicht zu einer größeren von uns aufgezogenen
 * Klassenstruktur. Eine Besonderheit sind evtl. noch unsere „Services“. Dieser gehört z.B. unser ImageLoader an. Wenn
 * eine Instanz des Image Loaders erstellt wird lädt dieser sofort alle Images vor, so das diese im Spielverlauf
 * schneller abgerufen werden können. Auch sitzt hier z.B. unser SceneSwitcher (enum) der den Wechsel zwischen zwei
 * Views ermöglicht. Er lädt die View Templates aus den fxml Dateien und ruft diese auf. Diese fxml Dateien starten dann
 * die dazugehörige View Klasse. Im Bezug auf die public / private definition von Variablen und Methoden haben wir
 * versucht es so um zu setzen das wir alles auf private gesetzt haben, außer die Werte die von Subklassen benutzt
 * werden. Wenn wir an private Variablen einer Klasse aus einer anderen heraus ran müssen dann haben wir public Getter /
 * Setter eingesetzt.
 */

public class Main extends Application {

   public static Stage stage;

   private static View mainView;
   public static GameController gameController;
   private static LighthouseService lighthouseService = new LighthouseService();

   @Override
   public void start(Stage primaryStage) throws Exception {

      stage = primaryStage;

      SceneSwitcher.MENU.load();

      stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
         @Override
         public void handle(WindowEvent event) {
            Platform.exit();
            System.exit(0);
         }
      });

   }

   /**
    * Centers the Window (Stage) on the users Desktop
    */
   public static void centerFrame() {

      Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

      stage.setX(primaryScreenBounds.getMaxX() / 2 - stage.getWidth() / 2);
      stage.setY(primaryScreenBounds.getMaxY() / 2 - stage.getHeight() / 2);


   }

   public static void main(String[] args) {
      launch(args);
   }

   public static View getMainView() {
      return mainView;
   }

   public static void setMainView(View mainView) {
      Main.mainView = mainView;
   }

   public static LighthouseService getLighthouseService() {
      return lighthouseService;
   }

   public static void setLighthouseService(LighthouseService lighthouseService) {
      Main.lighthouseService = lighthouseService;
   }

}
