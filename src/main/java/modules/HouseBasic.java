package modules;

import java.io.*;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class HouseBasic {
    protected long houseId;
    protected String housePicLink;
    protected String address;
    protected String Name;

    public HouseBasic(long houseId, String housePicLink, String address, String name) {
        this.houseId = houseId;
        this.housePicLink = housePicLink;
        this.address = address;
        Name = name;
    }
    public HouseBasic(){

    }

    public long getHouseId() {
        return houseId;
    }

    public String getHousePicLink() {
        if(housePicLink!=null&&housePicLink.equals("")){
            return "img/error-404.png";
        }
        return housePicLink;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return Name;
    }

    public double getEstimatedPrice(HouseBean hb){
        HouseBasic h = new HouseBasic();
        h.writeCsv(hb);

        try {
            Process process= null;
            String cd = "cd /home/temp";
            process = Runtime.getRuntime().exec(cd);
            process.waitFor();

            String command2 = "/bin/sh price.sh";
            process = Runtime.getRuntime().exec(command2);
            process.waitFor();
        }catch(Exception e){
            return 100001.0;
        }

        int price = h.readCsv();
        return price;
    }

    public void writeCsv(HouseBean hb){
        String outputFile = "/home/temp/test.csv";
        try {
            // use FileWriter constructor that specifies open for appending
            CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile), ',');
            // if the file didn't already exist then we need to write out the header line
            csvOutput.write("city");
            csvOutput.write("state");
            csvOutput.write("property_type");
            csvOutput.write("zipcode");
            csvOutput.write("room_type");
            csvOutput.write("accommodates");
            csvOutput.write("bathrooms");
            csvOutput.write("bedrooms");
            csvOutput.write("beds");
            csvOutput.endRecord();
            // else assume that the file already has the correct header line
            String roomType = hb.getRoom_type();

            // write out a few records
            csvOutput.write(hb.getCity());
            csvOutput.write(hb.getState());
            csvOutput.write(hb.getProperty_type());
            csvOutput.write(hb.getZipcode());
            csvOutput.write(hb.getRoom_type());
            csvOutput.write(hb.getGuests_includes());
            csvOutput.write(hb.getBathrooms());
            csvOutput.write(hb.getBedrooms());
            csvOutput.write(hb.getBeds());
            csvOutput.endRecord();
            csvOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int readCsv(){
        String price = "-1";
        try {
            FileReader fileReader = new FileReader("/home/temp/result.csv");
            BufferedReader bufferedReader =  new BufferedReader(fileReader);
            price = bufferedReader.readLine();
            // Always close files.
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (int)Float.parseFloat(price);
    }




    public static void main(String args[]) {
        String price = "-1";
        try {
            FileReader fileReader = new FileReader("asada");
            BufferedReader bufferedReader =  new BufferedReader(fileReader);
            price = bufferedReader.readLine();
            System.out.println((int)Float.parseFloat(price));
            // Always close files.
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println((double)(int)Float.parseFloat(price));
        } catch (IOException e) {
            System.out.println((int)Float.parseFloat(price));
        }
    }
}
