package puzzles.slide.ptui;

import java.awt.event.ActionListener;
import javax.swing.*;


import puzzles.common.Observer;
import puzzles.slide.model.SlideModel;
import puzzles.slide.model.SlideClientData;

import java.io.IOException;
import java.util.Scanner;

import static jdk.internal.org.jline.utils.Log.error;

public class SlidePTUI implements Observer<SlideModel, SlideClientData> {
    private SlideModel model;

    public SlidePTUI(String filename) throws IOException {
        this.model=new SlideModel(filename);
        System.out.println(this.model.toString());
        init();

    }


    public void init() throws IOException {
        this.model.addObserver(this);
        displayHelp();
    }

    @Override
    public void update(SlideModel model, SlideClientData data) {
        // for demonstration purposes
        System.out.println(data);
        System.out.println(model);
    }

    private void displayHelp() {
        System.out.println( "h(int)              -- hint next move" );
        System.out.println( "l(oad) filename     -- load new puzzle file" );
        System.out.println( "s(elect) r c        -- select cell at r, c" );
        System.out.println( "q(uit)              -- quit the game" );
        System.out.println( "r(eset)             -- reset the current game" );
    }

    public void run() throws IOException {
        Scanner in = new Scanner( System.in );
        for ( ; ; ) {
            System.out.print( "> " );
            String line = in.nextLine();
            String[] words = line.split( "\\s+" );
            if (words.length > 0) {
                if (words[0].startsWith( "q" )) {
                    break;
                }
                else {
                    switch (words[0]){
                        case "h":
                            System.out.println(this.model.hint());
                            break;
                        case "r":
                            System.out.println(this.model.reset());
                            break;
                        case "s":
                            System.out.println(this.model.selelct(Integer.parseInt(words[1]),Integer.parseInt(words[2])));
                            break;
                        case "l":
                            System.out.println("loaded: "+words[1]);
                            this.model=new SlideModel(words[1]);
                            System.out.println(this.model.toString());
                            break;
                        default:
                            System.out.println("idk this command plz try again");
                    }
                    System.out.flush();
                }
            }
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java JamPTUI filename");
        } else {
            try {
                SlidePTUI ptui = new SlidePTUI(args[0]);
                System.out.println("Loaded: "+args[0]);
                ptui.run();
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        }
    }
}

