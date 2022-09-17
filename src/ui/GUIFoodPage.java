package ui;

import controller.Main;
import database.DatabaseConnectionHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static ui.GUIWeaponsPage.BACKGROUND_IMAGE_FILENAME;

public class GUIFoodPage extends JPanel {
    Graphics g = null;
    public BufferedImage bgImage;
    public BufferedImage backBtnImage;
    public static final int W = 500;
    public static final int H = 700;
    public static final int BORDER_X = 53;
    public static final int BORDER_Y = 55;
    //    public static final int TEXT_FIELD_NAME_W = 280 / 2 - 5;
    public static final int TEXT_FIELD_X = 120;
    public static final int TEXT_FIELD_W = 280;
    public static final int TEXT_FIELD_H = 30;

    public static final int TEXT_FIELD_MARGIN_TOP = 20;
    public static final int TEXT_FIELD_MARGIN_LEFT = 10;
    public static final int TEXT_FIELD_USERNAME_Y = 210;
    public static final int TEXT_FIELD_EMAIL_Y = TEXT_FIELD_USERNAME_Y + TEXT_FIELD_MARGIN_TOP;
    public static final int TEXT_FIELD_PASSWORD_Y = TEXT_FIELD_EMAIL_Y + TEXT_FIELD_MARGIN_TOP;
    public static final int TEXT_FIELD_DISPLAY_NAME_Y = TEXT_FIELD_PASSWORD_Y + TEXT_FIELD_MARGIN_TOP;


//    private JTextField foodToDeleteText;
    public JComboBox<String> foodToDeleteText;
    //private JTextField attributeText;
    //private JTextField conditionText;

    //Table drop downs:
    public JComboBox<String> tableDropDown;

    //Attribute drop downs:
    public JComboBox<String> attributeDropDownFood;
    public JComboBox<String> attributeDropDownConsumes;

    //Condition drop downs:
    public JComboBox<String> conditionDropDownFoodName;
    public JComboBox<String> conditionDropDownFoodHealAmount;
    public JComboBox<String> conditionDropDownConsumesName;
    public JComboBox<String> conditionDropDownConsumesAmount;

    private JButton showInfoButton;
    private JButton deleteButton;
    private JButton playersWithAllFoodButton;
    private JTable characterTable;

    private JButton returnButton;
    private ArrayList<Object> playerInfo = null;
    private ArrayList<String> playersWithAllFoodList = null;

    DatabaseConnectionHandler dbHandler;

    public GUIFoodPage(DatabaseConnectionHandler dbHandler) {
        this.dbHandler = dbHandler;

        setLayout(null);
        this.setBackground(new Color(255, 255, 255));
        this.setBounds(0, 0, GUIMainPage.W, GUIMainPage.H);
        Main.frame.add(this, 0);

        foodToDeleteText = new JComboBox<>(new String[]{"-------", "Mushroom Pizza", "Jade Parcels", "Fullmoon Egg", "Teyvat Fried Egg",
        "Butter Crab", "Crystal Shrimp", "Five Pickled Treasures", "Grilled Tiger Fish", "Lotus Flower Crisp"});
        foodToDeleteText.setBounds(TEXT_FIELD_X, TEXT_FIELD_MARGIN_TOP, TEXT_FIELD_W, TEXT_FIELD_H);
        this.add(foodToDeleteText);

//        foodToDeleteText = new JTextField("Food to delete");
//        foodToDeleteText.setEnabled(false);
//        foodToDeleteText.setDisabledTextColor(Color.gray);
//        foodToDeleteText.setBounds(TEXT_FIELD_X, TEXT_FIELD_MARGIN_TOP, TEXT_FIELD_W, TEXT_FIELD_H);
//        foodToDeleteText.setBorder(BorderFactory.createLineBorder(Color.lightGray, 2, true));
//        foodToDeleteText.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                foodToDeleteText.requestFocus();
//                foodToDeleteText.setEnabled(true);
//            }
//        });

        //---------------------------------------------------------------------
        // read images
        //---------------------------------------------------------------------
        try { // background image
            bgImage = ImageIO.read(new File(BACKGROUND_IMAGE_FILENAME));
        } catch (IOException e) {
            System.out.println("GUIFoodPage::GUIFoodPage(): error: file not found: " + BACKGROUND_IMAGE_FILENAME);
            System.exit(1);
        }

        try { // back button image
            backBtnImage = ImageIO.read(new File(GUIMainPage.BACK_BTN_IMAGE_FILENAME));
        } catch (IOException e) {
            System.out.println("GUIAbilitiesPage::GUIAbilitiesPage(): error: file not found: " + GUIMainPage.BACK_BTN_IMAGE_FILENAME);
            System.exit(1);
        }

        //---------------------------------------------------------------------
        // init JComponents
        //---------------------------------------------------------------------
//        tableText = new JTextField("Table Name");
//        tableText.setEnabled(false);
//        tableText.setDisabledTextColor(Color.gray);
//        tableText.setBounds(TEXT_FIELD_X, TEXT_FIELD_MARGIN_TOP, TEXT_FIELD_W, TEXT_FIELD_H);
//        tableText.setBorder(BorderFactory.createLineBorder(Color.lightGray, 2, true));
//        tableText.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                tableText.requestFocus();
//                tableText.setEnabled(true);
//            }
//        });

//        //Tables:

//        //tableDropDown;
//        //attributeDropDownFood;
//        //attributeDropDownConsumes;
//        conditionDropDownFoodName;
//        conditionDropDownFoodHealAmount;
//        conditionDropDownConsumesName;
//        conditionDropDownConsumesAmount;

        tableDropDown = new JComboBox<>(new String[]{"pick a table", "food (all foods)", "consumes (your consumed food)"});
        tableDropDown.setBounds(TEXT_FIELD_X, TEXT_FIELD_MARGIN_TOP + TEXT_FIELD_H + 10, TEXT_FIELD_W, TEXT_FIELD_H);
        tableDropDown.setVisible(true);

        attributeDropDownFood = new JComboBox<>(new String[]{"pick an attribute to apply condition for food", "food name", "food heal amount"});
        attributeDropDownFood.setBounds(TEXT_FIELD_X, TEXT_FIELD_MARGIN_TOP + TEXT_FIELD_H + 10 + TEXT_FIELD_H + 10, TEXT_FIELD_W, TEXT_FIELD_H);
        attributeDropDownFood.setVisible(false);

        attributeDropDownConsumes = new JComboBox<>(new String[]{"pick an attribute to apply condition for consumes", "consumes food name", "consumes food amount"});
        attributeDropDownConsumes.setBounds(TEXT_FIELD_X, TEXT_FIELD_MARGIN_TOP + TEXT_FIELD_H + 10 + TEXT_FIELD_H + 10 , TEXT_FIELD_W, TEXT_FIELD_H);
        attributeDropDownConsumes.setVisible(false);

        conditionDropDownFoodName = new JComboBox<>(new String[]{"pick a condition for food name", "all types of pizzas", "all types of eggs"});
        conditionDropDownFoodName.setBounds(TEXT_FIELD_X, TEXT_FIELD_MARGIN_TOP + TEXT_FIELD_H + 10 + TEXT_FIELD_H + 10 + TEXT_FIELD_H + 10, TEXT_FIELD_W, TEXT_FIELD_H);
        conditionDropDownFoodName.setVisible(false);

        conditionDropDownFoodHealAmount = new JComboBox<>(new String[]{"pick a condition for food heal amount", "foods with heal amount > 100", "foods with heal amount = 450"});
        conditionDropDownFoodHealAmount.setBounds(TEXT_FIELD_X, TEXT_FIELD_MARGIN_TOP + TEXT_FIELD_H + 10 + TEXT_FIELD_H + 10 + TEXT_FIELD_H + 10, TEXT_FIELD_W, TEXT_FIELD_H);
        conditionDropDownFoodHealAmount.setVisible(false);

        conditionDropDownConsumesName = new JComboBox<>(new String[]{"pick a condition for consumes name", "only your food names", "all your pizzas"});
        conditionDropDownConsumesName.setBounds(TEXT_FIELD_X, TEXT_FIELD_MARGIN_TOP + TEXT_FIELD_H + 10 + TEXT_FIELD_H + 10  + TEXT_FIELD_H + 10, TEXT_FIELD_W, TEXT_FIELD_H);
        conditionDropDownConsumesName.setVisible(false);

        conditionDropDownConsumesAmount = new JComboBox<>(new String[]{"pick a condition for consumes amount", "all your food with amount > 2", "all your food with amount > 5"});
        conditionDropDownConsumesAmount.setBounds(TEXT_FIELD_X, TEXT_FIELD_MARGIN_TOP + TEXT_FIELD_H + 10 + TEXT_FIELD_H + 10 + TEXT_FIELD_H + 10, TEXT_FIELD_W, TEXT_FIELD_H);
        conditionDropDownConsumesAmount.setVisible(false);


        ActionListener tableDropDownListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String s = (String) tableDropDown.getSelectedItem();//get the selected item
                String table;

                switch (s) {//check for a match
                    case "food (all foods)":
                        table = "food";
                        attributeDropDownFood.setVisible(true);
                        attributeDropDownConsumes.setVisible(false);
                        conditionDropDownFoodName.setVisible(false);
                        conditionDropDownFoodHealAmount.setVisible(false);
                        conditionDropDownConsumesName.setVisible(false);
                        conditionDropDownConsumesAmount.setVisible(false);
                        goToClickFood(table);

                        break;
                    case "consumes (your consumed food)":
                        table = "consumes";
                        attributeDropDownFood.setVisible(false);
                        attributeDropDownConsumes.setVisible(true);
                        conditionDropDownFoodName.setVisible(false);
                        conditionDropDownFoodHealAmount.setVisible(false);
                        conditionDropDownConsumesName.setVisible(false);
                        conditionDropDownConsumesAmount.setVisible(false);
                        goToClickConsumes(table);
                        break;
                    default:
//                        for (int i = 0; i < maxLevelLabels.length; i++) {
//                            maxLevelLabels[i].setText(" ");
//                        }
                        break;
                }
            }


            private void goToClickFood(String table) {
                //attributeDropDown = new JComboBox<>(new String[]{"-------", "food name", "heal amount"});
                //attributeDropDown.setBounds(TEXT_FIELD_X, TEXT_FIELD_MARGIN_TOP + TEXT_FIELD_H + 10 + TEXT_FIELD_H + 10, TEXT_FIELD_W, TEXT_FIELD_H);

                ActionListener attributeDropDownFoodListener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        String s = (String) attributeDropDownFood.getSelectedItem();//get the selected item
                        String attribute;
                        switch (s) {//check for a match
                            case "food name":
                                attribute = "name";
                                conditionDropDownFoodName.setVisible(true);
                                conditionDropDownFoodHealAmount.setVisible(false);
                                conditionDropDownConsumesName.setVisible(false);
                                conditionDropDownConsumesAmount.setVisible(false);
                                goToClickFoodName(table, attribute);
                                break;
                            case "food heal amount":
                                attribute = "name";
                                conditionDropDownFoodName.setVisible(false);
                                conditionDropDownFoodHealAmount.setVisible(true);
                                conditionDropDownConsumesName.setVisible(false);
                                conditionDropDownConsumesAmount.setVisible(false);
                                goToClickFoodAmount(table, attribute);
                                break;
                            default:
//                        for (int i = 0; i < maxLevelLabels.length; i++) {
//                            maxLevelLabels[i].setText(" ");
//                        }
                                break;
                        }
                    }

                    private void goToClickFoodName(String table, String attribute) {
                        //conditionDropDown = new JComboBox<>(new String[]{"-------", "size of name > 5", "size of name > 25"});
                        //conditionDropDown.setBounds(TEXT_FIELD_X, TEXT_FIELD_MARGIN_TOP + TEXT_FIELD_H + 10 + TEXT_FIELD_H  +10 + TEXT_FIELD_H + 10, TEXT_FIELD_W, TEXT_FIELD_H);
                        ActionListener conditionDropDownFoodNameListener = new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                String s = (String) conditionDropDownFoodName.getSelectedItem();//get the selected item
                                String condition;

                                switch (s) {//check for a match
                                    case "all types of pizzas":
                                        condition = "name = 'Mushroom Pizza' OR name = 'Octopus Pizza'";
                                        playerInfo = dbHandler.getPlayerInfo(table, attribute, condition);
                                        break;
                                    case "all types of eggs":
                                        condition = "name = 'Fullmoon Egg' OR name = 'Teyvat Fried Egg'";
                                        playerInfo = dbHandler.getPlayerInfo(table, attribute, condition);
                                        break;
                                    default:
//                        for (int i = 0; i < maxLevelLabels.length; i++) {
//                            maxLevelLabels[i].setText(" ");
//                        }
                                        break;
                                }
                            }
                        };
                        conditionDropDownFoodName.addActionListener(conditionDropDownFoodNameListener);
                    }



                    private void goToClickFoodAmount(String table, String attribute) {
                        //conditionDropDown = new JComboBox<>(new String[]{"-------", "heal amount > 100", "heal amount = 450"});
                        //conditionDropDown.setBounds(TEXT_FIELD_X, TEXT_FIELD_MARGIN_TOP + TEXT_FIELD_H + 10 + TEXT_FIELD_H + 10 + TEXT_FIELD_H + 10, TEXT_FIELD_W, TEXT_FIELD_H);
                        ActionListener conditionDropDownFoodHealAmountListener = new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                String s = (String) conditionDropDownFoodHealAmount.getSelectedItem();//get the selected item
                                String condition;

                                switch (s) {//check for a match
                                    case "foods with heal amount > 100":
                                        condition = "healAmount > 100";
                                        playerInfo = dbHandler.getPlayerInfo(table, attribute, condition);
                                        break;
                                    case "foods with heal amount = 450":
                                        condition = "healAmount = 450";
                                        playerInfo = dbHandler.getPlayerInfo(table, attribute, condition);
                                        break;
                                    default:
//                        for (int i = 0; i < maxLevelLabels.length; i++) {
//                            maxLevelLabels[i].setText(" ");
//                        }
                                        break;
                                }
                            }
                        };
                        conditionDropDownFoodHealAmount.addActionListener(conditionDropDownFoodHealAmountListener);

                    }

                };
                attributeDropDownFood.addActionListener(attributeDropDownFoodListener);
            }

            private void goToClickConsumes(String table) {
                //attributeDropDown = new JComboBox<>(new String[]{"-------", "food name", "food amount"});
                //attributeDropDown.setBounds(TEXT_FIELD_X, TEXT_FIELD_MARGIN_TOP + TEXT_FIELD_H + 10 + TEXT_FIELD_H + 10, TEXT_FIELD_W, TEXT_FIELD_H);
                ActionListener attributeDropDownConsumesListener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        String s = (String) attributeDropDownConsumes.getSelectedItem();//get the selected item
                        String attribute;
                        switch (s) {//check for a match
                            case "consumes food name":
                                attribute = "fname";
                                conditionDropDownFoodName.setVisible(false);
                                conditionDropDownFoodHealAmount.setVisible(false);
                                conditionDropDownConsumesName.setVisible(true);
                                conditionDropDownConsumesAmount.setVisible(false);
                                goToClickConsumesName(table, attribute);
                                break;
                            case "consumes food amount":
                                attribute = "fname";
                                conditionDropDownFoodName.setVisible(false);
                                conditionDropDownFoodHealAmount.setVisible(false);
                                conditionDropDownConsumesName.setVisible(false);
                                conditionDropDownConsumesAmount.setVisible(true);
                                goToClickConsumesAmount(table, attribute);
                                break;
                            default:
//                        for (int i = 0; i < maxLevelLabels.length; i++) {
//                            maxLevelLabels[i].setText(" ");
//                        }
                                break;
                        }
                    }

                    private void goToClickConsumesName(String table, String attribute) {
                        //conditionDropDown = new JComboBox<>(new String[]{"-------", "size of name > 5", "size of name > 25"});
                        //conditionDropDown.setBounds(TEXT_FIELD_X, TEXT_FIELD_MARGIN_TOP + TEXT_FIELD_H + 10 + TEXT_FIELD_H + 10  + TEXT_FIELD_H + 10, TEXT_FIELD_W, TEXT_FIELD_H);
                        ActionListener conditionDropDownConsumesNameListener = new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                String s = (String) conditionDropDownConsumesName.getSelectedItem();//get the selected item
                                String condition;

                                switch (s) {//check for a match
                                    //case "size of name > 5":
                                    case "only your food names":
                                        condition = "username = '" + Main.currPlayer.getUsername() + "'";
                                        playerInfo = dbHandler.getPlayerInfo(table, attribute, condition);
                                        break;
                                    case "all your pizzas":
                                        condition = "(fname = 'Mushroom Pizza' OR fname = 'Octopus Pizza') AND username = '" + Main.currPlayer.getUsername() + "'";
                                        playerInfo = dbHandler.getPlayerInfo(table, attribute, condition);
                                        break;
                                    default:
//                        for (int i = 0; i < maxLevelLabels.length; i++) {
//                            maxLevelLabels[i].setText(" ");
//                        }
                                        break;
                                }
                            }
                        };
                        conditionDropDownConsumesName.addActionListener(conditionDropDownConsumesNameListener);
                    }

                    private void goToClickConsumesAmount(String table, String attribute) {
                        //conditionDropDown = new JComboBox<>(new String[]{"-------", "food amount > 0", "food amount > 5"});
                        //conditionDropDown.setBounds(TEXT_FIELD_X, TEXT_FIELD_MARGIN_TOP + TEXT_FIELD_H + 10 + TEXT_FIELD_H + 10 + TEXT_FIELD_H + 10, TEXT_FIELD_W, TEXT_FIELD_H);
                        ActionListener conditionDropDownConsumesAmountListener = new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                String s = (String) conditionDropDownConsumesAmount.getSelectedItem();//get the selected item
                                String condition;

                                switch (s) {//check for a match
                                    case "all your food with amount > 2":
                                        condition = "amount > 2 AND username = '" + Main.currPlayer.getUsername() + "'";
                                        playerInfo = dbHandler.getPlayerInfo(table, attribute, condition);
                                        break;
                                    case "all your food with amount > 5":
                                        condition = "amount > 5 AND username = '" + Main.currPlayer.getUsername() + "'";
                                        playerInfo = dbHandler.getPlayerInfo(table, attribute, condition);
                                        break;
                                    default:
//                        for (int i = 0; i < maxLevelLabels.length; i++) {
//                            maxLevelLabels[i].setText(" ");
//                        }
                                        break;
                                }
                            }
                        };
                        conditionDropDownConsumesAmount.addActionListener(conditionDropDownConsumesAmountListener);
                    }


                }; ///STOPS HERE
                attributeDropDownConsumes.addActionListener(attributeDropDownConsumesListener);
            }



        };
        tableDropDown.addActionListener(tableDropDownListener);



        this.add(tableDropDown);


        //Delete Button:
        deleteButton = new JButton("Delete");
        deleteButton.setBounds(TEXT_FIELD_X + 300, TEXT_FIELD_MARGIN_TOP, 200, TEXT_FIELD_H);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dbHandler.deleteConsumes(Main.currPlayer.getUsername(), (String)foodToDeleteText.getSelectedItem());
            }
        });


        // Show player info button:
        showInfoButton = new JButton("Show Info");
        showInfoButton.setBounds(TEXT_FIELD_X + 300, TEXT_FIELD_MARGIN_TOP + TEXT_FIELD_H, 200, TEXT_FIELD_H);

        Object[][] s = new Object[50][50];
        Object[] c = {"Table", "Attribute"};
        characterTable = new JTable(s,c);
        characterTable.setVisible(true);
        characterTable.setBackground(Color.white);
        characterTable.setBounds(TEXT_FIELD_X, TEXT_FIELD_MARGIN_TOP + TEXT_FIELD_H + 150, 300, 250);

        showInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //clears the table in a super scuffed way
                for (int i = 0; i < 50; i++) {
                    for (int j = 0; j < 50; j++) {
                        s[i][j] = "";
                    }
                }

                characterTable.revalidate();
                characterTable.updateUI();
                for (int i = 0; i < playerInfo.size(); i++) {
//                    System.out.println(characters.size());
                    s[i][0] = String.valueOf(playerInfo.get(i));
                }

                characterTable.revalidate();
                characterTable.updateUI();

            }
        });


        //Players with all food button :

        playersWithAllFoodButton = new JButton("Show all players who own all food");
        playersWithAllFoodButton.setBounds(TEXT_FIELD_X + 300 , TEXT_FIELD_MARGIN_TOP + TEXT_FIELD_H + TEXT_FIELD_H, 300, TEXT_FIELD_H);


        playersWithAllFoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playersWithAllFoodList = dbHandler.getPlayersWithAllFood();

                //clears the table in a super scuffed way
                for (int i = 0; i < 50; i++) {
                    for (int j = 0; j < 50; j++) {
                        s[i][j] = "";
                    }
                }
                characterTable.revalidate();
                characterTable.updateUI();
                for (int i = 0; i < playersWithAllFoodList.size(); i++) {
//                    System.out.println(characters.size());
                    s[i][0] = playersWithAllFoodList.get(i);
                    //s[i][0] = String.valueOf(playerInfo.get(i));
                }

                characterTable.revalidate();
                characterTable.updateUI();

            }
        });

        returnButton = new JButton(new ImageIcon(backBtnImage));
        returnButton.setBounds(GUIMainPage.BTN_BACK_X, GUIMainPage.BTN_BACK_Y, GUIMainPage.BTN_BACK_W, GUIMainPage.BTN_BACK_H);
        returnButton.setBorder(BorderFactory.createEmptyBorder());
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.changeScreen(2);
                Main.guiFoodPage.setVisible(false);
            }
        });
        this.add(returnButton);

        //this.add(attributeText);

        //add drop downs:
        this.add(tableDropDown);
        this.add(attributeDropDownFood);
        this.add(attributeDropDownConsumes);
        this.add(conditionDropDownFoodName);
        this.add(conditionDropDownFoodHealAmount);
        this.add(conditionDropDownConsumesName);
        this.add(conditionDropDownConsumesAmount);


        this.add(foodToDeleteText);
        this.add(deleteButton);
        this.add(showInfoButton);
        this.add(playersWithAllFoodButton);
        this.add(characterTable);

        repaint();

    }

    public void paint(Graphics g) {
        g.drawImage(bgImage, 0, 0, null);

        paintComponents(g);
    }
}