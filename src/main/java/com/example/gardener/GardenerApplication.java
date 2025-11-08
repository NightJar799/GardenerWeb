package com.example.gardener;

import com.example.gardener.Entities.Plant;
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

        Plant plant = new Plant();
        plant.setName("Test Plant");
        plant.setScienceName("Test");
        plant.setDescription("A test plant for demonstration");

        Plant savedPlant = plantService.addPlant(plant);
        System.out.println("plant ID: " + savedPlant.getId());

        context.close();

	}

}
