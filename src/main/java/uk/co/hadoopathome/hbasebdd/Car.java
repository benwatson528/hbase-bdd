package uk.co.hadoopathome.hbasebdd;

public class Car {
    private String registration;
    private String model;
    private int productionYear;

    public Car(String registration, String model, int productionYear) {
        this.registration = registration;
        this.model = model;
        this.productionYear = productionYear;
    }

    public String getRegistration() {
        return registration;
    }

    public String getModel() {
        return model;
    }

    public int getProductionYear() {
        return productionYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (productionYear != car.productionYear) return false;
        if (registration != null ? !registration.equals(car.registration) : car.registration != null) return false;
        return model != null ? model.equals(car.model) : car.model == null;
    }
}
