package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
	 
	   
	 List<Double> przelicznikiArray = new ArrayList<>();
	 List<Double> kursyArray = new ArrayList<>();
	 List<String> walutyList = new ArrayList<>();
	 List<Double> przelicznikiArraya = new ArrayList<>();
	 List<Double> kursyArraya = new ArrayList<>();
	 List<String> walutyLista = new ArrayList<>();
	 List<Double> przelicznikiArrayb = new ArrayList<>();
	 List<Double> kursyArrayb = new ArrayList<>();
	 List<String> walutyListb = new ArrayList<>();
        private double wartosc=0;
        private double wartoscz=0;
        private double wartoscna=0;
        private double przelicznik;
        private double kurs;
        private double przelicznika;
        private double kursa;
        private double przelicznikb;
        private double kursb;
	    private final String NBP_URLA = "http://www.nbp.pl/kursy/xml/lasta.xml";
	    private final String NBP_URLB = "https://www.nbp.pl/kursy/xml/lastb.xml";
	    private String selectedCurrency = "THB";
	    private boolean jakibutton = false;
	    int selectedIndex=0;
	    public void initialize() {
	    	  updateCurrencyTypeChoices();
	    	kursa=kursyArraya.get(0);
	    	kursb=kursyArrayb.get(0);
	    	przelicznikb=przelicznikiArrayb.get(0);
	    	przelicznika=przelicznikiArraya.get(0);
	    	
	    	
	    	tableType.getItems().add("Tabela A");
	    	tableType.getItems().add("Tabela B");
	    	tableType.setValue(tableType.getItems().get(0));
	    	walutaType.setValue("THB");

	    	wartosc1.setText("0");
	    	wartosc2.setText("0");
	    	typwartosci2.setText("PLN");
	    	typwartosci1.setText("THB");
	    	ValueTable.setPromptText("0");
	    	przelicznikiArray.clear();
    		przelicznikiArray.addAll(przelicznikiArraya);
    		kursyArray.clear();
    		kursyArray.addAll(kursyArraya);
    		walutyList.clear();
    		walutyList.addAll(walutyLista);
    		walutaType.getItems().clear();
	    	walutaType.getItems().addAll(walutyList);
	    	 przelicznik=przelicznika;
	          kurs=kursa;
	          walutaType.setValue("THB");

		    	wartosc1.setText("0");
		    	wartosc2.setText("0");
		    	typwartosci2.setText("PLN");
		    	typwartosci1.setText("THB");
		    	ValueTable.setPromptText("0");
		    
	    	tableType.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
	    	    
	    	        // Tutaj logika, która ma być wykonana przy zmianie zaznaczenia
	    	    	String selectedTableType = tableType.getValue();
	    	    	if(selectedTableType=="Tabela A") {
	    	    		przelicznikiArray.clear();
	    	    		przelicznikiArray.addAll(przelicznikiArraya);
	    	    		kursyArray.clear();
	    	    		kursyArray.addAll(kursyArraya);
	    	    		walutyList.clear();
	    	    		walutyList.addAll(walutyLista);
	    	    		walutaType.getItems().clear();
	    		    	walutaType.getItems().addAll(walutyList);
	    		    	 przelicznik=przelicznika;
	    		          kurs=kursa;
	    		          walutaType.setValue("THB");
                            wartosc=0;
	    			    	wartosc1.setText("0");
	    			    	wartosc2.setText("0");
	    			    	typwartosci2.setText("PLN");
	    			    	typwartosci1.setText("THB");
	    			    	ValueTable.setPromptText("0");
	    			    	ValueTable.setText("0");
	    	    	}
                    if(selectedTableType=="Tabela B") {
                    	
                    	przelicznik=przelicznikb;
	    		         kurs=kursb;
	    		         wartosc=0;
                    	przelicznikiArray.clear();
	    	    		przelicznikiArray.addAll(przelicznikiArrayb);
	    	    		kursyArray.clear();
	    	    		kursyArray.addAll(kursyArrayb);
	    	    		walutyList.clear();
	    	    		walutyList.addAll(walutyListb);
	    	    		walutaType.getItems().clear();
            	    	walutaType.getItems().addAll(walutyList);
            	    	walutaType.setValue("AFN");

            	    	wartosc1.setText("0");
            	    	wartosc2.setText("0");
            	    	typwartosci2.setText("PLN");
            	    	typwartosci1.setText("AFN");
            	    	ValueTable.setPromptText("0");
            	    	ValueTable.setText("0");
	    	    	}
	    	    
	    	});
	    	
	        // Inicjalizacja ChoiceBox tableType i pobranie danych z NBP
	       

	        // Inicjalizacja waluty bez ingerencji przycisków typu radio
	      
	       
	        // Dodanie listenerów dla RadioButton (opcjonalnie, jeśli chcesz)
	        // ...
	        ToggleGroup toggleGroup = new ToggleGroup();
	        NaPLNType.setToggleGroup(toggleGroup);
	        ZPLNType.setToggleGroup(toggleGroup);
	        toggleGroup.selectToggle(NaPLNType);
	     
	        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
	        	String selectedTableType = tableType.getValue();
	        	tableType.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
	        		
	        		toggleGroup.selectToggle(NaPLNType);
	        	});
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
	                		
	                		kurs =  kursyArray.get(selectedIndex);
	                		 przelicznik =  przelicznikiArray.get(selectedIndex);
	                		wartoscna=(kurs/przelicznik)*wartosc;
	                		DecimalFormat decimalFormat = new DecimalFormat("#.##");
	                		 String sformatowanaLiczba = decimalFormat.format(wartoscna);
	                		wartosc2.setText(sformatowanaLiczba);
	                		

	                		
	                	}
	                	if(jakibutton==true) {
	                		kurs =  kursyArray.get(selectedIndex);
	                		 przelicznik =  przelicznikiArray.get(selectedIndex);
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
	                		
	                		kurs =  kursyArray.get(selectedIndex);
	                		 przelicznik =  przelicznikiArray.get(selectedIndex);
	                		wartoscna=(kurs/przelicznik)*wartosc;
	                		DecimalFormat decimalFormat = new DecimalFormat("#.##");
	                		 String sformatowanaLiczba = decimalFormat.format(wartoscna);
	                		wartosc2.setText(sformatowanaLiczba);
	                		

	                		
	                	}
	                	if(jakibutton==true) {
	                		kurs =  kursyArray.get(selectedIndex);
	                		 przelicznik =  przelicznikiArray.get(selectedIndex);
	                		wartoscz=(wartosc*przelicznik)/kurs;
	                		DecimalFormat decimalFormat = new DecimalFormat("#.##");
	                		 String sformatowanaLiczba = decimalFormat.format(wartoscz);
	                		wartosc2.setText(sformatowanaLiczba);
	                	}
	            }
	            // Tutaj możesz dodać dodatkową logikę w zależności od zmiany stanu
	            System.out.println("isZPLNSelected: " + jakibutton);
	        });
	        
	        
	        
	        tableType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	            System.out.println("Zmieniono opcję na: " + newValue);
	           
	            
	           
		       
	            
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
                		
                		kurs =  kursyArray.get(selectedIndex);
                		 przelicznik =  przelicznikiArray.get(selectedIndex);
                		wartoscna=(kurs/przelicznik)*wartosc;
                		DecimalFormat decimalFormat = new DecimalFormat("#.##");
                		 String sformatowanaLiczba = decimalFormat.format(wartoscna);
                		wartosc2.setText(sformatowanaLiczba);
                		

                		
                	}
                	if(jakibutton==true) {
                		kurs =  kursyArray.get(selectedIndex);
                		 przelicznik =  przelicznikiArray.get(selectedIndex);
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
		                		
		                		kurs =  kursyArray.get(selectedIndex);
		                		 przelicznik =  przelicznikiArray.get(selectedIndex);
		                		wartoscna=(kurs/przelicznik)*wartosc;
		                		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		                		 String sformatowanaLiczba = decimalFormat.format(wartoscna);
		                		wartosc2.setText(sformatowanaLiczba);
		                		

		                		
		                	}
		                	if(jakibutton==true) {
		                		kurs =  kursyArray.get(selectedIndex);
		                		 przelicznik =  przelicznikiArray.get(selectedIndex);
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
    
	  

	    private void updateCurrencyTypeChoices() {
	    	
	    	
	    		 
	        walutaType.getItems().clear();
	       
	        
	        try {
	            Document doc = Jsoup.connect(NBP_URLA).get();
	            Elements currencies = doc.select("pozycja");
	            Document docb = Jsoup.connect(NBP_URLB).get();
 	            Elements currenciesb = docb.select("pozycja");
	            for (Element currency : currencies) {
	            	int indexo = 0;
	                String code = currency.select("kod_waluty").text();
	                String przelicznikStr = currency.select("przelicznik").text();
	               
	                przelicznikStr = przelicznikStr.replace(",", ".");
	               
	                String kursstr = currency.select("kurs_sredni").text();
	                kursstr = kursstr.replace(",", ".");
	             
	                przelicznika=Double.parseDouble(przelicznikStr);
	                kursa=Double.parseDouble(kursstr);
	                
	                
	               
	                przelicznikiArraya.add(przelicznika);
	                kursyArraya.add(kursa);
	                walutyLista.add(code);
	               
	                indexo++;
	              
	            }
	            for (Element currencyb : currenciesb) {
	            	int indexo = 0;
 	                String codeb = currencyb.select("kod_waluty").text();
 	                String przelicznikStrb = currencyb.select("przelicznik").text();
 	               
 	                przelicznikStrb = przelicznikStrb.replace(",", ".");
 	               
 	                String kursstrb = currencyb.select("kurs_sredni").text();
 	                kursstrb = kursstrb.replace(",", ".");
 	            
 	                przelicznikb=Double.parseDouble(przelicznikStrb);
 	                kursb=Double.parseDouble(kursstrb);
 	                
 	               przelicznikiArrayb.add(przelicznikb);
	                kursyArrayb.add(kursb);
	                walutyListb.add(codeb);
 	                indexo++;
 	                
 	            }
	            
	           

	            // Przywrócenie wcześniej wybranej waluty
	            
	        } catch (IOException e) {
	        	  e.printStackTrace();

	        	    // Tworzenie okienka dialogowego z błędem
	        	    Alert alert = new Alert(Alert.AlertType.ERROR);
	        	    alert.setTitle("Błąd");
	        	    alert.setHeaderText("Błąd podczas ładowania danych");
	        	    alert.setContentText("Nie udało się pozyskać danych z Internetu.");

	        	    // Wyświetlenie okienka
	        	    alert.showAndWait();
	        }
	    	
	    	
	    }
	}