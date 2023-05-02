package com.moonlabcoding.calculatriceappwithhistorique;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Déclaration des éléments de l'interface utilisateur
    private TextView txtMainActOp, txtMainActRes;
    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private Button btnPlus, btnSous, btnMul, btnDiv, btnPoint, btnAC, btnBack, btnEgale;

    private ListView listViewHistorique;
    private List<Operation> historique;
    private HistoriqueAdapter historiqueAdapter;

    private boolean over = false;

    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;
            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }
            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }
            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected:" + (char)ch);
                return x;
            }
            // Grammar:
// expression = term | expression `+` term | expression `-` term
// term = factor | term `*` factor | term `/` factor
// factor = `+` factor | `-` factor | `(` expression `)` | number
// | functionName `(` expression `)` | functionName factor
// | factor `^` factor
            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }
            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }
            double parseFactor() {
                if (eat('+')) return +parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus
                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();if (!eat(')')) throw new RuntimeException("Missing ')'");
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    if (eat('(')) {
                        x = parseExpression();
                        if (!eat(')')) throw new RuntimeException("Missing ')'after argument to " + func);
                    } else {
                        x = parseFactor();
                    }
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x =
                            Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x =
                            Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x =
                            Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " +
                                func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }
                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation
                return x;
            }
        }.parse();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listViewHistorique = findViewById(R.id.lsvOperations);


        historique = new ArrayList<>();
        historiqueAdapter = new HistoriqueAdapter(historique);
        listViewHistorique.setAdapter(historiqueAdapter);

        txtMainActOp = findViewById(R.id.txtMainActOp);
        txtMainActRes = findViewById(R.id.txtMainActRes);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        btnPlus = findViewById(R.id.btnPlus);
        btnSous = findViewById(R.id.btnSous);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);
        btnPoint = findViewById(R.id.btnPoint);
        btnAC = findViewById(R.id.btnAC);
        btnBack = findViewById(R.id.btnBack);
        btnEgale = findViewById(R.id.btnEgale);

        // Ajout des EventListeners aux boutons
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action à effectuer lorsque le bouton 0 est cliqué
                // Action à effectuer lorsque le bouton 0 est cliqué
                if (over) {
                    txtMainActOp.setText("0");
                    over = false;
                } else {
                    String currentText = txtMainActOp.getText().toString();
                    txtMainActOp.setText(currentText + "0");
                }
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action à effectuer lorsque le bouton 1 est cliqué
                // Action à effectuer lorsque le bouton 0 est cliqué
                if (over) {
                    txtMainActOp.setText("1");
                    over = false;
                } else {
                    String currentText = txtMainActOp.getText().toString();
                    txtMainActOp.setText(currentText + "1");
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action à effectuer lorsque le bouton 1 est cliqué
                // Action à effectuer lorsque le bouton 0 est cliqué
                if (over) {
                    txtMainActOp.setText("2");
                    over = false;
                } else {
                    String currentText = txtMainActOp.getText().toString();
                    txtMainActOp.setText(currentText + "2");
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action à effectuer lorsque le bouton 1 est cliqué
                // Action à effectuer lorsque le bouton 0 est cliqué
                if (over) {
                    txtMainActOp.setText("3");
                    over = false;
                } else {
                    String currentText = txtMainActOp.getText().toString();
                    txtMainActOp.setText(currentText + "3");
                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action à effectuer lorsque le bouton 1 est cliqué
                // Action à effectuer lorsque le bouton 0 est cliqué
                if (over) {
                    txtMainActOp.setText("4");
                    over = false;
                } else {
                    String currentText = txtMainActOp.getText().toString();
                    txtMainActOp.setText(currentText + "4");
                }
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action à effectuer lorsque le bouton 1 est cliqué
                // Action à effectuer lorsque le bouton 0 est cliqué
                if (over) {
                    txtMainActOp.setText("5");
                    over = false;
                } else {
                    String currentText = txtMainActOp.getText().toString();
                    txtMainActOp.setText(currentText + "5");
                }
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action à effectuer lorsque le bouton 1 est cliqué
                // Action à effectuer lorsque le bouton 0 est cliqué
                if (over) {
                    txtMainActOp.setText("6");
                    over = false;
                } else {
                    String currentText = txtMainActOp.getText().toString();
                    txtMainActOp.setText(currentText + "6");
                }
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action à effectuer lorsque le bouton 1 est cliqué
                // Action à effectuer lorsque le bouton 0 est cliqué
                if (over) {
                    txtMainActOp.setText("7");
                    over = false;
                } else {
                    String currentText = txtMainActOp.getText().toString();
                    txtMainActOp.setText(currentText + "7");
                }
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action à effectuer lorsque le bouton 1 est cliqué
                // Action à effectuer lorsque le bouton 0 est cliqué
                if (over) {
                    txtMainActOp.setText("8");
                    over = false;
                } else {
                    String currentText = txtMainActOp.getText().toString();
                    txtMainActOp.setText(currentText + "8");
                }
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action à effectuer lorsque le bouton 1 est cliqué
                // Action à effectuer lorsque le bouton 0 est cliqué
                if (over) {
                    txtMainActOp.setText("9");
                    over = false;
                } else {
                    String currentText = txtMainActOp.getText().toString();
                    txtMainActOp.setText(currentText + "9");
                }
            }
        });


        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action à effectuer lorsque le bouton "+" est cliqué
                if (over) {
                    txtMainActOp.setText("+");
                    over = false;
                } else {
                    String currentText = txtMainActOp.getText().toString();
                    txtMainActOp.setText(currentText + "+");
                }
            }
        });

        btnSous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action à effectuer lorsque le bouton "-" est cliqué
                if (over) {
                    txtMainActOp.setText("-");
                    over = false;
                } else {
                    String currentText = txtMainActOp.getText().toString();
                    txtMainActOp.setText(currentText + "-");
                }
            }
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action à effectuer lorsque le bouton "x" est cliqué
                if (over) {
                    txtMainActOp.setText("*");
                    over = false;
                } else {
                    String currentText = txtMainActOp.getText().toString();
                    txtMainActOp.setText(currentText + "*");
                }
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action à effectuer lorsque le bouton "/" est cliqué
                if (over) {
                    txtMainActOp.setText("/");
                    over = false;
                } else {
                    String currentText = txtMainActOp.getText().toString();
                    txtMainActOp.setText(currentText + "/");
                }
            }
        });

        btnPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action à effectuer lorsque le bouton "." est cliqué
                if (over) {
                    txtMainActOp.setText(".");
                    over = false;
                } else {
                    String currentText = txtMainActOp.getText().toString();
                    txtMainActOp.setText(currentText + ".");
                }
            }
        });

        btnAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action à effectuer lorsque le bouton "AC" est cliqué
                txtMainActOp.setText("");
                txtMainActRes.setText("");
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action à effectuer lorsque le bouton "Back" est cliqué
                String currentText = txtMainActOp.getText().toString();
                if (!currentText.isEmpty()) {
                    // Supprimer le dernier caractère de la chaîne de texte
                    String newText = currentText.substring(0, currentText.length() - 1);
                    // Mettre à jour le champ de texte avec la nouvelle chaîne
                    txtMainActOp.setText(newText);
                }
            }
        });

        btnEgale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action à effectuer lorsque le bouton "=" est cliqué
                // Action à effectuer lorsque le bouton "=" est cliqué
                String expression = txtMainActOp.getText().toString();
                double result = eval(expression);
                txtMainActRes.setText(String.valueOf(result));
                Operation historiqueItem = new Operation(expression, String.valueOf(result));
                historique.add(historiqueItem);
                historiqueAdapter.notifyDataSetChanged();
                over = true;
            }
        });




    }
}