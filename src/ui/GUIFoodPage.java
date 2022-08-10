package ui;

import controller.Main;
import database.DatabaseConnectionHandler;
import model.Weapon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GUIFoodPage extends JPanel {
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


    private JTextField tableText;
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
    private JTable characterTable;

    private JButton returnButton;
    private ArrayList<Object> playerInfo = null;

    DatabaseConnectionHandler dbHandler;






    public GUIFoodPage(DatabaseConnectionHandler dbHandler) {
        this.dbHandler = dbHandler;

        setLayout(null);
        this.setBackground(new Color(255, 255, 255));
        this.setBounds(0, 0, GUIMainPage.W, GUIMainPage.H);
        Main.frame.add(this, 0);

        tableText = new JTextField("Table Name");
        tableText.setEnabled(false);
        tableText.setDisabledTextColor(Color.gray);
        tableText.setBounds(TEXT_FIELD_X, TEXT_FIELD_MARGIN_TOP, TEXT_FIELD_W, TEXT_FIELD_H);
        tableText.setBorder(BorderFactory.createLineBorder(Color.lightGray, 2, true));
        tableText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tableText.requestFocus();
                tableText.setEnabled(true);
            }
        });

//        //Tables:

//        //tableDropDown;
//        //attributeDropDownFood;
//        //attributeDropDownConsumes;
//        conditionDropDownFoodName;
//        conditionDropDownFoodHealAmount;
//        conditionDropDownConsumesName;
//        conditionDropDownConsumesAmount;

        tableDropDown = new JComboBox<>(new String[]{"-------", "food", "consumes"});
        tableDropDown.setBounds(TEXT_FIELD_X, TEXT_FIELD_MARGIN_TOP + TEXT_FIELD_H + 10, TEXT_FIELD_W, TEXT_FIELD_H);
        tableDropDown.setVisible(true);

        attributeDropDownFood = new JComboBox<>(new String[]{"-------", "food name", "food heal amount"});
        attributeDropDownFood.setBounds(TEXT_FIELD_X, TEXT_FIELD_MARGIN_TOP + TEXT_FIELD_H + 10 + TEXT_FIELD_H + 10, TEXT_FIELD_W, TEXT_FIELD_H);
        attributeDropDownFood.setVisible(false);

        attributeDropDownConsumes = new JComboBox<>(new String[]{"-------", "food name", "food amount"});
        attributeDropDownConsumes.setBounds(TEXT_FIELD_X, TEXT_FIELD_MARGIN_TOP + TEXT_FIELD_H + 10 + TEXT_FIELD_H + 10 , TEXT_FIELD_W, TEXT_FIELD_H);
        attributeDropDownConsumes.setVisible(false);

        conditionDropDownFoodName = new JComboBox<>(new String[]{"-------", "size of name > 5", "size of name > 25"});
        conditionDropDownFoodName.setBounds(TEXT_FIELD_X, TEXT_FIELD_MARGIN_TOP + TEXT_FIELD_H + 10 + TEXT_FIELD_H + 10 + TEXT_FIELD_H + 10, TEXT_FIELD_W, TEXT_FIELD_H);
        conditionDropDownFoodName.setVisible(false);

        conditionDropDownFoodHealAmount = new JComboBox<>(new String[]{"-------", "heal amount > 100", "heal amount = 450"});
        conditionDropDownFoodHealAmount.setBounds(TEXT_FIELD_X, TEXT_FIELD_MARGIN_TOP + TEXT_FIELD_H + 10 + TEXT_FIELD_H + 10 + TEXT_FIELD_H + 10, TEXT_FIELD_W, TEXT_FIELD_H);
        conditionDropDownFoodHealAmount.setVisible(false);

        conditionDropDownConsumesName = new JComboBox<>(new String[]{"-------", "only your food names", "all pizza names"});
        conditionDropDownConsumesName.setBounds(TEXT_FIELD_X, TEXT_FIELD_MARGIN_TOP + TEXT_FIELD_H + 10 + TEXT_FIELD_H + 10  + TEXT_FIELD_H + 10, TEXT_FIELD_W, TEXT_FIELD_H);
        conditionDropDownConsumesName.setVisible(false);

        conditionDropDownConsumesAmount = new JComboBox<>(new String[]{"-------", "food amount > 0", "food amount > 5"});
        conditionDropDownConsumesAmount.setBounds(TEXT_FIELD_X, TEXT_FIELD_MARGIN_TOP + TEXT_FIELD_H + 10 + TEXT_FIELD_H + 10 + TEXT_FIELD_H + 10, TEXT_FIELD_W, TEXT_FIELD_H);
        conditionDropDownConsumesAmount.setVisible(false);


        ActionListener tableDropDownListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String s = (String) tableDropDown.getSelectedItem();//get the selected item
                String table;

                switch (s) {//check for a match
                    case "food":
                        table = "food";
                        attributeDropDownFood.setVisible(true);
                        goToClickFood(table);

                        break;
                    case "consumes":
                        table = "consumes";
                        attributeDropDownConsumes.setVisible(true);
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
                                goToClickFoodName(table, attribute);
                                break;
                            case "food heal amount":
                                attribute = "healAmount";
                                conditionDropDownFoodHealAmount.setVisible(true);
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
                                    case "size of name > 5":
                                        condition = "name = 'Mushroom Pizza'";
                                        playerInfo = dbHandler.getPlayerInfo(table, attribute, condition);
                                        break;
                                    case "size of name > 25":
                                        condition = "name.length() > 25";
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
                                    case "heal amount > 100":
                                        condition = "healAmount > 100";
                                        playerInfo = dbHandler.getPlayerInfo(table, attribute, condition);
                                        break;
                                    case "heal amount = 450":
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
                            case "food name":
                                attribute = "fname";
                                conditionDropDownConsumesName.setVisible(true);
                                goToClickConsumesName(table, attribute);
                                break;
                            case "food amount":
                                attribute = "amount";
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
                                        condition = "username = 'player2'";
                                        playerInfo = dbHandler.getPlayerInfo(table, attribute, condition);
                                        break;
                                    case "all pizza names":
                                        condition = "fname = 'Mushroom Pizza' AND fname = 'Octopus Pizza'";
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
                                    case "food amount > 0":
                                        condition = "amount > 0";
                                        playerInfo = dbHandler.getPlayerInfo(table, attribute, condition);
                                        break;
                                    case "food amount > 5":
                                        condition = "amount > 5";
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



        showInfoButton = new JButton("Show Info");
        showInfoButton.setBounds(TEXT_FIELD_X + 300, TEXT_FIELD_MARGIN_TOP, 80, TEXT_FIELD_H);

        Object[][] s = new Object[50][50];
        Object[] c = {"Table", "Attribute"};
        characterTable = new JTable(s,c);
        characterTable.setVisible(true);
        characterTable.setBackground(Color.white);
        characterTable.setBounds(TEXT_FIELD_X, TEXT_FIELD_MARGIN_TOP + TEXT_FIELD_H + 100, 500, 500);

        showInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //String username = userText.getText();
                //int minATK = Integer.parseInt(atkText.getText());

//                weapons = dbHandler.giveOwnedWeaponWithMinATK(minATK, username);
//
//                for (int i = 0; i < weapons.size() - 1; i++) {
//                    s[i][0] = weapons.get(i).getName();
//                    s[i][1] = String.valueOf(weapons.get(i).getBaseATK());
//                }

                //clears the table in a super scuffed way
                for (int i = 0; i < 50; i++) {
                    for (int j = 0; j < 50; j++) {
                        s[i][j] = "";
                    }
                }


                characterTable.revalidate();
                characterTable.updateUI();

                        //playerInfo = dbHandler.getPlayerInfo(tableText.getText(), attributeText.getText(), conditionText.getText());





                for (int i = 0; i < playerInfo.size(); i++) {
//                    System.out.println(characters.size());
                    s[i][0] = String.valueOf(playerInfo.get(i));
                }

                characterTable.revalidate();
                characterTable.updateUI();

            }
        });

        returnButton = new JButton("Return");
        returnButton.setBounds(GUIMainPage.BTN_BACK_X, GUIMainPage.BTN_BACK_Y, GUIMainPage.BTN_BACK_W + 50, GUIMainPage.BTN_BACK_H);
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




        this.add(tableText);
        //this.add(conditionText);
        this.add(showInfoButton);
        this.add(characterTable);

    }
}







//Old:

//        attributeText = new JTextField("Attribute");
//        attributeText.setEnabled(false);
//        attributeText.setDisabledTextColor(Color.gray);
//        attributeText.setBounds(TEXT_FIELD_X, TEXT_FIELD_MARGIN_TOP + TEXT_FIELD_H + 10, TEXT_FIELD_W, TEXT_FIELD_H);
//        attributeText.setBorder(BorderFactory.createLineBorder(Color.lightGray, 2, true));
//        attributeText.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                attributeText.requestFocus();
//                attributeText.setEnabled(true);
//            }
//        });

//        conditionText = new JTextField("Condition");
//        conditionText.setEnabled(false);
//        conditionText.setDisabledTextColor(Color.gray);
//        conditionText.setBounds(TEXT_FIELD_X, TEXT_FIELD_MARGIN_TOP + TEXT_FIELD_H + 10 + TEXT_FIELD_H + 10, TEXT_FIELD_W, TEXT_FIELD_H);
//        conditionText.setBorder(BorderFactory.createLineBorder(Color.lightGray, 2, true));
//        conditionText.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                conditionText.requestFocus();
//                conditionText.setEnabled(true);
//            }
//        });



//After adding table drop down (from sally's):
//        tfMinMaxLevel = new JTextField();
//        tfMinMaxLevel.setEnabled(false);
//        tfMinMaxLevel.setBounds(TF_MIN_MAX_LEVEL_BY_X, TF_MIN_MAX_LEVEL_BY_Y, TF_MIN_MAX_LEVEL_BY_W, TF_MIN_MAX_LEVEL_BY_H);
//        tfMinMaxLevel.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (tableDropDown.getSelectedItem() == "max character lvl") {
//                    tfMinMaxLevel.requestFocus();
//                    tfMinMaxLevel.setEnabled(true);
//                }
//            }
//        });
//        KeyListener tfKeyListener = new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//
//            }
//
//            public void keyPressed(KeyEvent e) {
//                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
//                    try {
//                        Integer.parseInt(tfMinMaxLevel.getText());
//                    } catch(NumberFormatException exc){
//                        System.out.println("GUIPartiesPage::line 292: Invalid input. Please enter a number.");
//                        return;
//                    }
//                    dbHandler.strongestCharacterLevelInParty(Main.currPlayer, Integer.parseInt(tfMinMaxLevel.getText()));
//                }
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//
//            }
//        };
//        tfMinMaxLevel.addKeyListener(tfKeyListener);
//        this.add(tfMinMaxLevel);