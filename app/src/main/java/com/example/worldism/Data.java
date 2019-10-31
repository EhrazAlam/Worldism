package com.example.worldism;

import java.io.Serializable;
import java.util.ArrayList;

public class Data implements Serializable {

        private String Cname;
        private String Cimage;
        private String Ccapital;
        private String Cpopulation;
        private String Ccontinent;
        private String Carea;
        private ArrayList Ccurrency;
        private ArrayList Clanguage;


        public Data(String cname, String cimage, String ccapital, String cpopulation, String ccontinent, String carea, ArrayList<String> ccurrency, ArrayList<String> clanguage) {
            this.setCname(cname);
            this.setCimage(cimage);
            this.setCcapital(ccapital);
            this.setClanguage(clanguage);
            this.setCpopulation(cpopulation);
            this.setCcurrency(ccurrency);
            this.setCcontinent(ccontinent);
            this.setCarea(carea);
        }


        public String getCname() {
            return Cname;
        }

        public void setCname(String cname) {
            Cname = cname;
        }

        public String getCimage() {
            return Cimage;
        }

        public void setCimage(String cimage) {
            Cimage = cimage;
        }

        public String getCcapital() {
            return Ccapital;
        }

        public void setCcapital(String ccapital) {
            Ccapital = ccapital;
        }

        public String getCpopulation() {
            return Cpopulation;
        }

        public void setCpopulation(String cpopulation) {
            Cpopulation = cpopulation;
        }

    public String getCcontinent() {
        return Ccontinent;
    }

    public void setCcontinent(String ccontinent) {
        Ccontinent = ccontinent;
    }

    public String getCarea() {
        return Carea;
    }

    public void setCarea(String carea) {
        Carea = carea;
    }


    public ArrayList<String> getClanguage() {
            return Clanguage;
        }

        public void setClanguage(ArrayList<String> clanguage) {
            Clanguage = clanguage;
        }

        public ArrayList<String> getCcurrency() {
            return Ccurrency;
        }

        public void setCcurrency(ArrayList<String> ccurrency) {
            Ccurrency = ccurrency;
        }

}
