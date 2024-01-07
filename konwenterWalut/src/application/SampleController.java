package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
public class SampleController {
	
	@FXML
    private ChoiceBox<String> tableType;
	@FXML
    private ChoiceBox<String> walutaType;
	@FXML
	private RadioButton NaPLNType;
	@FXML
	private RadioButton ZPLNType;
	 @FXML
	    private TextField ValueTable;
	 @FXML
	    private Text wartosc1;
	 @FXML
	    private Text wartosc2;
	 @FXML
	    private Text typwartosci1;
	 @FXML
	    private Text typwartosci2;
	 
	   
	 private double[] przelicznikiArray; 
	 private double[] kursyArray;
	   
        private double wartosc=0;
        private double wartoscz=0;
        private double wartoscna=0;
        private double przelicznik=1;
        private double kurs=0.1148;
	    private final String NBP_URL = "http://www.nbp.pl/kursy/xml/lasta.xml";
	    private String selectedCurrency = "THB";
	    private boolean jakibutton = false;
	    int selectedIndex=0;
	    public void initialize() {
	    	walutaType.setValue("THB");

	    	wartosc1.setText("0");
	    	wartosc2.setText("0");
	    	typwartosci2.setText("PLN");
	    	typwartosci1.setText("THB");
	    	ValueTable.setPromptText("0");
	        // Inicjalizacja ChoiceBox tableType i pobranie danych z NBP
	        initializeTableType();

	        // Inicjalizacja waluty bez ingerencji przycisków typu radio
	        updateCurrencyTypeChoices("PLN");

	        // Dodanie listenerów dla RadioButton (opcjonalnie, jeśli chcesz)
	        // ...
	        ToggleGroup toggleGroup = new ToggleGroup();
	        NaPLNType.setToggleGroup(toggleGroup);
	        ZPLNType.setToggleGroup(toggleGroup);
	        toggleGroup.selectToggle(NaPLNType);
	        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
	            if (toggleGroup.getSelectedToggle() == NaPLNType) {
	            	jakibutton = false;
	            	 if(jakibutton==true) {
	 		            typwartosci2.setText(selectedCurrency); // Aktualizacja wartości Text na podstawie wybranego elementu w ChoiceBox
	 		            typwartosci1.setText("PLN");
	 		        	}
	 		        	if(jakibutton==false) {
	 		        	     typwartosci1.setText(selectedCurrency); // Aktualizacja wartości Text na podstawie wybranego elementu w ChoiceBox
	 		 	            typwartosci2.setText("PLN");
	 		        		
	 		        		
	 		        	}
	 		        	if(jakibutton==false) {
	                		
	                		kurs =  kursyArray[selectedIndex];
	                		 przelicznik =  przelicznikiArray[selectedIndex];
	                		wartoscna=(kurs/przelicznik)*wartosc;
	                		DecimalFormat decimalFormat = new DecimalFormat("#.##");
	                		 String sformatowanaLiczba = decimalFormat.format(wartoscna);
	                		wartosc2.setText(sformatowanaLiczba);
	                		

	                		
	                	}
	                	if(jakibutton==true) {
	                		kurs =  kursyArray[selectedIndex];
	                		 przelicznik =  przelicznikiArray[selectedIndex];
	                		wartoscz=(wartosc*przelicznik)/kurs;
	                		DecimalFormat decimalFormat = new DecimalFormat("#.##");
	                		 String sformatowanaLiczba = decimalFormat.format(wartoscz);
	                		wartosc2.setText(sformatowanaLiczba);
	                	}
	            } else if (toggleGroup.getSelectedToggle() == ZPLNType) {
	            	jakibutton = true;
	            	if(jakibutton==true) {
	 		            typwartosci2.setText(selectedCurrency); // Aktualizacja wartości Text na podstawie wybranego elementu w ChoiceBox
	 		            typwartosci1.setText("PLN");
	 		        	}
	 		        	if(jakibutton==false) {
	 		        	     typwartosci1.setText(selectedCurrency); // Aktualizacja wartości Text na podstawie wybranego elementu w ChoiceBox
	 		 	            typwartosci2.setText("PLN");
	 		        		
	 		        		
	 		        	}
	 		        	if(jakibutton==false) {
	                		
	                		kurs =  kursyArray[selectedIndex];
	                		 przelicznik =  przelicznikiArray[selectedIndex];
	                		wartoscna=(kurs/przelicznik)*wartosc;
	                		DecimalFormat decimalFormat = new DecimalFormat("#.##");
	                		 String sformatowanaLiczba = decimalFormat.format(wartoscna);
	                		wartosc2.setText(sformatowanaLiczba);
	                		

	                		
	                	}
	                	if(jakibutton==true) {
	                		kurs =  kursyArray[selectedIndex];
	                		 przelicznik =  przelicznikiArray[selectedIndex];
	                		wartoscz=(wartosc*przelicznik)/kurs;
	                		DecimalFormat decimalFormat = new DecimalFormat("#.##");
	                		 String sformatowanaLiczba = decimalFormat.format(wartoscz);
	                		wartosc2.setText(sformatowanaLiczba);
	                	}
	            }
	            // Tutaj możesz dodać dodatkową logikę w zależności od zmiany stanu
	            System.out.println("isZPLNSelected: " + jakibutton);
	        });
	        // Listener dla wyboru waluty niezależnie od wciśniętego przycisku
	        walutaType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	            selectedCurrency = newValue;
	            selectedIndex = walutaType.getSelectionModel().getSelectedIndex();
	            System.out.println("magia " + selectedIndex);
	            if(jakibutton==true) {
		            typwartosci2.setText(newValue); // Aktualizacja wartości Text na podstawie wybranego elementu w ChoiceBox
		            typwartosci1.setText("PLN");
		        	}
		        	if(jakibutton==false) {
		        	     typwartosci1.setText(newValue); // Aktualizacja wartości Text na podstawie wybranego elementu w ChoiceBox
		 	            typwartosci2.setText("PLN");
		        		
		        		
		        	}
		        	if(jakibutton==false) {
                		
                		kurs =  kursyArray[selectedIndex];
                		 przelicznik =  przelicznikiArray[selectedIndex];
                		wartoscna=(kurs/przelicznik)*wartosc;
                		DecimalFormat decimalFormat = new DecimalFormat("#.##");
                		 String sformatowanaLiczba = decimalFormat.format(wartoscna);
                		wartosc2.setText(sformatowanaLiczba);
                		

                		
                	}
                	if(jakibutton==true) {
                		kurs =  kursyArray[selectedIndex];
                		 przelicznik =  przelicznikiArray[selectedIndex];
                		wartoscz=(wartosc*przelicznik)/kurs;
                		DecimalFormat decimalFormat = new DecimalFormat("#.##");
                		 String sformatowanaLiczba = decimalFormat.format(wartoscz);
                		wartosc2.setText(sformatowanaLiczba);
                	}
	        });
	        
	        ValueTable.setTextFormatter(new TextFormatter<>(change -> {
	            if (change.isContentChange()) {
	                String newText = change.getControlNewText();
	                if (newText.matches("\\d*\\.?\\d*")) {
	                	  double doubleValue = Double.parseDouble(newText);
		                	wartosc=doubleValue;
		                	if(jakibutton==false) {
		                		
		                		kurs =  kursyArray[selectedIndex];
		                		 przelicznik =  przelicznikiArray[selectedIndex];
		                		wartoscna=(kurs/przelicznik)*wartosc;
		                		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		                		 String sformatowanaLiczba = decimalFormat.format(wartoscna);
		                		wartosc2.setText(sformatowanaLiczba);
		                		

		                		
		                	}
		                	if(jakibutton==true) {
		                		kurs =  kursyArray[selectedIndex];
		                		 przelicznik =  przelicznikiArray[selectedIndex];
		                		wartoscz=(wartosc*przelicznik)/kurs;
		                		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		                		 String sformatowanaLiczba = decimalFormat.format(wartoscz);
		                		wartosc2.setText(sformatowanaLiczba);
		                	}
		                	 wartosc1.setText(Double.toString(wartosc));

	                    return change;
	                    
	                }
	                return null; // Odrzucenie zmiany, jeśli wprowadzony tekst nie jest liczbą
	            }
	            return change;
	        }));
       
	        
	    }

	    private void initializeTableType() {
	        try {
	            Document doc = Jsoup.connect(NBP_URL).get();
	            Elements tables = doc.select("tabela_kursow");

	            for (Element table : tables) {
	                String tableName = table.select("numer_tabeli").text();
	               
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    private void updateCurrencyTypeChoices(String currencyCode) {
	        walutaType.getItems().clear();
	        przelicznikiArray = new double[100];
	        kursyArray = new double[100];
	        int indexo = 0;
	        try {
	            Document doc = Jsoup.connect(NBP_URL).get();
	            Elements currencies = doc.select("pozycja");

	            for (Element currency : currencies) {
	                String code = currency.select("kod_waluty").text();
	                String przelicznikStr = currency.select("przelicznik").text();
	               
	                przelicznikStr = przelicznikStr.replace(",", ".");
	                System.out.println("testuj: " + przelicznikStr);
	                String kursstr = currency.select("kurs_sredni").text();
	                kursstr = kursstr.replace(",", ".");
	                System.out.println("testuj: " + kursstr);
	                przelicznik=Double.parseDouble(przelicznikStr);
	                kurs=Double.parseDouble(kursstr);
	                
	                przelicznikiArray[indexo] = przelicznik;
	                kursyArray[indexo] = kurs;
	                indexo++;
	                walutaType.getItems().add(code);
	            }

	            // Przywrócenie wcześniej wybranej waluty
	            if (!selectedCurrency.isEmpty() && walutaType.getItems().contains(selectedCurrency)) {
	                walutaType.setValue(selectedCurrency);
	            } else {
	                // Jeśli wcześniej nie było wybranej waluty, ustaw pierwszą dostępną
	                if (!walutaType.getItems().isEmpty()) {
	                    walutaType.setValue(walutaType.getItems().get(0));
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}