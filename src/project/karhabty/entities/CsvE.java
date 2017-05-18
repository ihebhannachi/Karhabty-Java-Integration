

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.entities;

import com.csvreader.CsvWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.beans.value.ObservableSetValue;
import javafx.collections.ObservableList;
import project.karhabty.services.VoituresServices;

/**
 *
 * @author KAMOUN
 */
public class CsvE {
       private final DateTimeFormatter dtf= DateTimeFormatter.ofPattern("dd-mm-yyyy");

  
                      
    public CsvE(ObservableList<Voiture> v) {
        // TODO code application logic here
        String outputFile = "CarsList.csv";
		//String g=;
		// before we open the file check to see if it already exists
		boolean alreadyExists = new File(outputFile).exists();
			
		try {
			// use FileWriter constructor that specifies open for appending
			  CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');
			
			// if the file didn't already exist then we need to write out the header line
			if (!alreadyExists)
			{
				csvOutput.write("Matricule");
				csvOutput.write("Marque");
                                csvOutput.write("Modele");
				csvOutput.write("Date de premiere mis en circulation");
				csvOutput.write("Puissance Fiscale");
				csvOutput.write("Puissance Dynamique");
				csvOutput.write("Carburant");
				csvOutput.write("Transmission");
				csvOutput.write("Kilometrage");

				csvOutput.endRecord();
			}
			// else assume that the file already has the correct header line
			//System.out.println(v.size());
                        csvOutput.write("Matricule");
				csvOutput.write("Marque");
                                csvOutput.write("Modele");
				csvOutput.write("Date de premiere mis en circulation");
				csvOutput.write("Puissance Fiscale");
				csvOutput.write("Puissance Dynamique");
				csvOutput.write("Carburant");
				csvOutput.write("Transmission");
				csvOutput.write("Kilometrage");

				csvOutput.endRecord();
                       for(int i=0;i<v.size();i++)
                        {
                         csvOutput.write(v.get(i).getMatricule());//get(i).getMatricule()
                           csvOutput.write(v.get(i).getMarqueS());
//                           String c=(v.getObs().get().getDateMiseCirculation()).toString(dtf);
                          // csvOutput.write();
                           csvOutput.write(Integer.toString(v.get(i).getPuissanceFiscale()));
                           csvOutput.write(Integer.toString(v.get(i).getPuissanceDynamique()));
                           csvOutput.write(v.get(i).getCarburant());
                           csvOutput.write(v.get(i).getTransmission());
                           csvOutput.endRecord();

                        }
			
			
			csvOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
 
}

