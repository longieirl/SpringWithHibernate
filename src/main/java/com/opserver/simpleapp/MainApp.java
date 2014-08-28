package com.opserver.simpleapp;

import com.opserver.simpleapp.commands.CommandEnum;
import com.opserver.simpleapp.commands.HelpCommand;
import com.opserver.simpleapp.services.CommandLineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;

@Component
public class MainApp {

    public static final Logger LOGGER = LoggerFactory.getLogger(MainApp.class);

    @Autowired
    private CommandLineService commandLineService;
    @Autowired
    private HelpCommand helpCommand;

    public static void main(String[] args) throws IOException {
        LOGGER.debug(">>main(args=" + args + ")");

        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        MainApp p = context.getBean(MainApp.class);
        p.start();

    }

    private void start() throws IOException {
        LOGGER.debug(">>start()");

        boolean running = true;

        helpCommand.execute(null);

        while (running) {

            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            String line = stdin.readLine();
            String[] input = line.split(" "); // Spilt by space

            try {

                CommandEnum commandEnum = CommandEnum.fromValue(Integer.parseInt(input[0]));
                if (commandEnum == null || line.length() == 0) {
                    System.out.println("Your option is not valid");
                } else {
                    // Get the receiving object and invoke the associating command selected
                    commandLineService.execute(input, commandEnum);
                }

            } catch (NumberFormatException e) {
                System.err.println("First argument must be an integer");
            } catch (InputMismatchException ex) {
                System.err.println("Did you enter a valid command?");
            }
        }
    }
}
