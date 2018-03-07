package com.example.valentinvasilescu.ceva;

import java.util.List;

/**
 * Created by Valentin Vasilescu on 8/18/2017.
 */

public class WeatherData {

    private Coord coord;

    private String base;
    private String name;
    private Main main;
    private List<Weather> weather;
    private Sys sys;

    public WeatherData(Coord coord, int code, String base, String name, Main main) {
        this.coord = coord;
        this.base = base;
        this.name = name;
        this.main = main;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    class Main {
        double temp;
        double pressure;
        int humidity;

        public Main(double temp, int pressure, int humidity) {
            this.temp = temp;
            this.pressure = pressure;
            this.humidity = humidity;
        }

        public double getTemp() {
            return temp;
        }

    }

    class Coord {
        double lat;
        double lon;

        public Coord(double lat, double lon) {
            this.lat = lat;
            this.lon = lon;
        }

        public double getLat() {
            return lat;
        }

        public double getLon() {
            return lon;
        }

    }

    class Weather {
        private int id;
        String main;
        String description;
        String icon;

        public Weather(int id, String main, String description, String icon) {
            this.id = id;
            this.main = main;
            this.description = description;
            this.icon = icon;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

    }

    class Sys {
        int type;
        int id;
        String country;
        long sunrise;
        long sunset;

        public Sys(int type, int id, String country, long sunrise, long sunset) {
            this.type = type;
            this.id = id;
            this.country = country;
            this.sunrise = sunrise;
            this.sunset = sunset;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCountry() {
            return country;
        }


        public long getSunrise() {
            return sunrise;
        }



        public long getSunset() {
            return sunset;
        }

    }

    public double getLat() {
        return coord.getLat();
    }

    public double getLon() {
        return coord.getLon();
    }

    public double getTemp() {
        return main.getTemp();
    }

    public String getCountry() {
        return sys.getCountry();
    }

    public long getSunrise() {
        return sys.getSunrise();
    }

    public long getSunset() {
        return sys.getSunset();
    }


}
