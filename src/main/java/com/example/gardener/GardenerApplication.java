package com.example.gardener;

import com.example.gardener.Entities.Plant;
import com.example.gardener.Entities.Preferences;
import com.example.gardener.Entities.User;
import com.example.gardener.service.PlantService;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class GardenerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =SpringApplication.run(GardenerApplication.class, args);

        PlantService plantService = context.getBean(PlantService.class);

        System.out.println("=== Simple PlantService Test ===");
//
//        Plant plant = new Plant();
//        plant.setName("Test Oak");
//        plant.setScienceName("Test oak");
//        plant.setDescription("A test oak for demonstration");
//
//        Plant savedPlant = plantService.addPlant(plant);
//        System.out.println("plant ID: " + savedPlant.getId());

        User user  = plantService.findUserByID(1);
//        User user1 = plantService.addUser(user);
        System.out.println("User ID: " + user.getId());
        Preferences pref = new Preferences(new User("aba", "daba"), "krutoy", "Russian", 10, "Plenty", "Greatrusfield");
        System.out.println(pref.getId());
        System.out.println("First try " + plantService.addPref(pref).getId());
        System.out.println("Pref ID: " + pref.getId());
//        plantService.addPref(pref);
        context.close();

	}

}
