
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author lalos
 */
public class ui extends javax.swing.JFrame {
    ArrayList<Character> alfabeto;
    ArrayList<Character> estados;
    ArrayList<String> nombreEstados;
    int inicial;
    ArrayList<Integer> finales;
    String tabla[][];
    int estadoActual;
    int col=3, fil=0;
    JFileChooser seleccionar;
    File archivo;
    /**
     * Creates new form uiAutomata
     */
    public ui() {
        initComponents();
        seleccionar = new JFileChooser();
        archivo = new File("");
        tabla=null;
        alfabeto= new ArrayList();
        estados= new ArrayList();
        finales= new ArrayList();
        nombreEstados = new ArrayList();
        cadena.setLineWrap(true);
        lexemas.setLineWrap(true);
    }
    
    public String AbrirArchivo(File Archivo){
        String documento=" ";
        FileInputStream entrada;
        try{
            entrada =  new FileInputStream(Archivo);
            documento = new Scanner(entrada).useDelimiter("\\A").next();
            entrada.close();
            
        }catch (IOException e){
            return e.getMessage();
        }
        return documento;
    }
    
    public void guardarTabla(String contenido){
        String[] lineas = contenido.split("\n");
        int i,j;
        String[] tmp;
        //Revisamos las lineas del documento
        //**********************************
        //Alfabeto
        //En la primera debe estar las letras del alfabeto separados por coma
        tmp = lineas[0].split(",");
        for(i=0; i<tmp.length; i++){
            System.out.println(tmp[i]);
            alfabeto.add(tmp[i].charAt(0));
        }
        
        //**********************************
        //Estados
        //En la segunda los estados, tambien serparados por coma
        tmp = lineas[1].split(",");
        for(i=0; i<tmp.length; i++){
            estados.add(tmp[i].charAt(0));
        }
        
        //*********************************
        //Estados nombre
        //En la tercera el nombre de los estados
        tmp = lineas[2].split(",");
        for(i=0; i<tmp.length; i++){
            nombreEstados.add(tmp[i]);
        }
        
        //*********************************
        //Estado inicial
        //En la tercera el estado inicial
        inicial = Integer.parseInt(lineas[3].trim());
        estadoActual= inicial;
        
        //***********************************
        //Estados finales
        //En la cuarta los estados finales
        tmp = lineas[4].split(",");
        for(i=0; i<tmp.length; i++){
            finales.add(Integer.parseInt(tmp[i].trim()));
        }
        
        //***********************************
        //Ahora leemos las operaciones de transición (tabla)
        //Ya conocemos las dimensiones de la tabla
        fil = estados.size();
        tabla = new String[fil][3];
        String[] valores;
        //Apartir de la quinta fila hasta la cantidad de estados
        for(i=0; i<fil; i++){
            valores = lineas[i+5].split(" ");
            
            //Es estado de entrada
            tabla[i][0]=valores[0].trim();
            //El caracter de transision
            tabla[i][1]=valores[1].trim();
            //El estado de salida
            tabla[i][2]=valores[2].trim();
        }
    }
    
    public int esAlfabeto(char letra){
        int i;
        Pattern dig= Pattern.compile("[0-9]");
        Pattern let =Pattern.compile("[a-zA-Z]");
        for(i=0; i<alfabeto.size(); i++){
            //Si letra es un digito
            Matcher D = dig.matcher(letra+"");
            Matcher L = let.matcher(letra+"");
            
            if(alfabeto.get(i)==letra){
                return i;
            }
            else
            if( D.find()){
                if(alfabeto.get(i)=='d'){
                    return i;
                }
            }
            else
             if( L.find()){
                if(alfabeto.get(i)=='l'){
                    return i;
                }
            }
        }
        return -1;
    }
    public int transision(int estadoA,char letra){
        int i;
        Pattern dig= Pattern.compile("[0-9]");
        Pattern let =Pattern.compile("[a-zA-Z]");
        Matcher D = dig.matcher(letra+"");
        Matcher L = let.matcher(letra+"");
        
        if( D.find()){
            letra='d';
        }
        else
            if( L.find()){
                letra= 'l';
            }
        for(i=0; i<estados.size(); i++){
            if(Integer.parseInt(tabla[i][0]) == estadoA && tabla[i][1].charAt(0) == letra){
                return Integer.parseInt(tabla[i][2]);
            }
        }
        return -1;
    }
    
    public int otro(int estadoA){
        int i;
        for(i=0; i<estados.size(); i++){
            //caso de otro
            if(Integer.parseInt(tabla[i][0]) == estadoA && tabla[i][1].charAt(0) == '@'){
                return Integer.parseInt(tabla[i][2]); 
            }
        }
        return -1;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        cadena = new javax.swing.JTextArea();
        consultar = new javax.swing.JButton();
        palabra = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        lexemas = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        abrirArchivo = new javax.swing.JMenuItem();

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 479, 205));
        getContentPane().setLayout(null);

        cadena.setEditable(false);
        cadena.setColumns(20);
        cadena.setRows(5);
        jScrollPane1.setViewportView(cadena);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(0, 11, 170, 153);

        consultar.setText("Verificar");
        consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarActionPerformed(evt);
            }
        });
        getContentPane().add(consultar);
        consultar.setBounds(340, 50, 71, 23);

        palabra.setMaximumSize(new java.awt.Dimension(6, 50));
        getContentPane().add(palabra);
        palabra.setBounds(340, 30, 128, 20);

        lexemas.setEditable(false);
        lexemas.setColumns(20);
        lexemas.setRows(5);
        jScrollPane2.setViewportView(lexemas);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(170, 10, 166, 150);

        jLabel2.setText("Ingresa una cadena");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(340, 10, 110, 14);

        jMenu5.setText("Archivo");

        abrirArchivo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        abrirArchivo.setText("Cargar Tabla");
        abrirArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirArchivoActionPerformed(evt);
            }
        });
        jMenu5.add(abrirArchivo);

        jMenuBar3.add(jMenu5);

        setJMenuBar(jMenuBar3);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void consultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarActionPerformed
        //guadamos los caracteres ingresados
        int posCol, nuevoEstado, posE=0;
        boolean esFinal=false;
        String aux="";
        String word = this.palabra.getText();
        if(word.isEmpty()){
            JOptionPane.showMessageDialog(null, "Ingresa un valor para copmrobar.");
        }else
        if(tabla == null){
            JOptionPane.showMessageDialog(null, "Favor de abrir un archivo.");
        }
        else
        {
            //Vaciamos las variables
            estadoActual=inicial;
            cadena.setText("");
            lexemas.setText("");
            //Hacemos un ciclo letra por letra
            char letra;
            for(int i=0; i<word.length(); i++){
                letra = word.charAt(i);
                aux+=letra;
                System.out.println("**************Leyendo siguiente letra***************");
                System.out.println("Estado actual: "+estadoActual);
                System.out.println("Letra ingresada: "+String.valueOf(letra));
                posCol = esAlfabeto(letra);
                nuevoEstado = transision(estadoActual,letra);
                
                if(posCol==-1){
                    JOptionPane.showMessageDialog(null, "La letra '"+letra+"' no es parte del alfabeto");
                    break;
                }
                else{
                    if(nuevoEstado == -1){
                        nuevoEstado = otro(estadoActual);
                        if(nuevoEstado == -1){
                            JOptionPane.showMessageDialog(null, "No hay transisión posible");
                            break;
                        }
                        i--; //Retroceso
                    }
                    System.out.println("Pasa a estado: "+String.valueOf(nuevoEstado));
                    
                    for(int j=0; j<finales.size(); j++){
                        if (nuevoEstado == finales.get(j)) {
                            esFinal=true;
                            posE=j;
                            break;
                        }
                    }
                    //Si es final
                    if(esFinal){
                        cadena.append(aux+"\n");
                        lexemas.append(nombreEstados.get(posE)+"\n");
                        aux="";
                        estadoActual=inicial;
                    }
                    //Si no es final
                    else {
                        estadoActual=nuevoEstado;
                        cadena.append(aux+"\n");
                        lexemas.append("\'\'\n");
                    }
                    esFinal=false;
                }
            }
        }
    }//GEN-LAST:event_consultarActionPerformed

    private void abrirArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirArchivoActionPerformed
        if(seleccionar.showDialog(null,"Abrir")==JFileChooser.APPROVE_OPTION){
            archivo=seleccionar.getSelectedFile();
            if(archivo.canRead()){
                if(archivo.getName().endsWith(".txt")){
                    alfabeto.clear();
                    estados.clear();
                    finales.clear();
                    String documento=AbrirArchivo(archivo);
                    guardarTabla(documento);
                }else{
                    JOptionPane.showMessageDialog(null,"Archivo no compatible");
                }
            }
        }
    }//GEN-LAST:event_abrirArchivoActionPerformed

    public static void main(String[] args) {
        ui w = new ui();
        w.setBounds(0, 0, 500, 230);
        w.setTitle("Analizador Léxico");
        w.setLocationRelativeTo(null);
        w.setVisible(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem abrirArchivo;
    private javax.swing.JTextArea cadena;
    private javax.swing.JButton consultar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea lexemas;
    private javax.swing.JTextField palabra;
    // End of variables declaration//GEN-END:variables
}