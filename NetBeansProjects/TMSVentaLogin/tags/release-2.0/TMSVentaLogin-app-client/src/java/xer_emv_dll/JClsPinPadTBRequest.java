/*
 * JClsPinPadTBRequest.java
 *
 * Created on 22 de septiembre de 2008, 01:39 PM
 *
 * Envio de información
 */

package xer_emv_dll;

/**
 *
 * @author ocruz
 */
public class JClsPinPadTBRequest {
    private xeremvcls emvcls;
    
    private String[] request;
    private String dbgSetUrl; // datosEMV[0];
    // Cantidad a cobrar en la tarjeta
    private String Tx_Amount; //  datosEMV[1];
    //Venta Directa Banda [sndVtaDirectaEMV]
    private String Bs_User; //  datosEMV[2];
    private String Bs_Pwd; //  datosEMV[3];
    private String Bs_UsrTransaction; // datosEMV[4];
    private String Bs_Company; // datosEMV[5];
    private String Bs_Branch; // datosEMV[6];
    private String Bs_Country; // datosEMV[7];
    private String Cc_Type; // datosEMV[8];
    private String Cc_Name; // datosEMV[9];
    private String Cc_Number; // datosEMV[10];
    private String Cc_ExpMonth; // datosEMV[11];
    private String Cc_ExpYear; // datosEMV[12];
    private String Cc_CvvCsc; // datosEMV[13];
    private String Tx_Merchant; // datosEMV[14];
    private String Tx_Reference; // datosEMV[15];
    private String Tx_OperationType; // datosEMV[16];
    private String Tx_Currency; // datosEMV[17];
    private String Tx_Room; // datosEMV[18];
    private String Tx_Waiter; // datosEMV[19];
    private String Tx_Shifts; // datosEMV[20];
    private String Tx_Tip; // datosEMV[21];
    private String Tx_OperationNumber; // datosEMV[22];
    private String Tx_Auth; // datosEMV[23];
    private String Tx_Date; // datosEMV[24];
    private String Avs_Adress; // datosEMV[25];
    private String Avs_District; // datosEMV[26];
    private String Avs_Municipality; // datosEMV[27];
    private String Avs_City; // datosEMV[28];
    private String Avs_State; // datosEMV[29];
    private String Avs_zip; // datosEMV[30];
    private String Cc_AMEXCvCsc; // datosEMV[31];
    // Modalidad
    private String pModo; // datosEMV[32];
    /**
     * Creates a new instance of JClsPinPadTBRequest
     */
    public JClsPinPadTBRequest() {
        emvcls = new xeremvcls();
    }

    public void setDataArray(String[] datosEMV){
        this.setRequestArray(datosEMV);
        // URL a enviar la solicitud
        this.setDbgSetUrl(datosEMV[0]);
        // Cantidad a cobrar en la tarjeta
        this.setTx_Amount(datosEMV[1]);
        //Venta Directa Banda [sndVtaDirectaEMV]
        this.setBs_User(datosEMV[2]);
        this.setBs_Pwd(datosEMV[3]);
        this.setBs_UsrTransaction(datosEMV[4]);
        this.setBs_Company(datosEMV[5]);
        this.setBs_Branch(datosEMV[6]);
        this.setBs_Country(datosEMV[7]);
        this.setCc_Type(datosEMV[8]);
        this.setCc_Name(datosEMV[9]);
        this.setCc_Number(datosEMV[10]);
        this.setCc_ExpMonth(datosEMV[11]);
        this.setCc_ExpYear(datosEMV[12]);
        this.setCc_CvvCsc(datosEMV[13]);
        this.setTx_Merchant(datosEMV[14]);
        this.setTx_Reference(datosEMV[15]);
        this.setTx_OperationType(datosEMV[16]);
        this.setTx_Currency(datosEMV[17]);
        this.setTx_Room(datosEMV[18]);
        this.setTx_Waiter(datosEMV[19]);
        this.setTx_Shifts(datosEMV[20]);
        this.setTx_Tip(datosEMV[21]);
        this.setTx_OperationNumber(datosEMV[22]);
        this.setTx_Auth(datosEMV[23]);
        this.setTx_Date(datosEMV[24]);
        this.setAvs_Adress(datosEMV[25]);
        this.setAvs_District(datosEMV[26]);
        this.setAvs_Municipality(datosEMV[27]);
        this.setAvs_City(datosEMV[28]);
        this.setAvs_State(datosEMV[29]);
        this.setAvs_zip(datosEMV[30]);
        this.setCc_AMEXCvCsc(datosEMV[31]);
        // Modalidad
        this.setPModo(datosEMV[32]);
    }
    
    private String[] getDataArray(){
        String[] datosEMV = new String[33];

        datosEMV[0]=this.getDbgSetUrl();
        datosEMV[1]=this.getTx_Amount();
        datosEMV[2]=this.getBs_User();
        datosEMV[3]=this.getBs_Pwd();
        datosEMV[4]=this.getBs_UsrTransaction();
        datosEMV[5]=this.getBs_Company();
        datosEMV[6]=this.getBs_Branch();
        datosEMV[7]=this.getBs_Country();
        datosEMV[8]=this.getCc_Type();
        datosEMV[9]=this.getCc_Name();
        datosEMV[10]=this.getCc_Number();
        datosEMV[11]=this.getCc_ExpMonth();
        datosEMV[12]=this.getCc_ExpYear();
        datosEMV[13]=this.getCc_CvvCsc();
        datosEMV[14]=this.getTx_Merchant();
        datosEMV[15]=this.getTx_Reference();
        datosEMV[16]=this.getTx_OperationType();
        datosEMV[17]=this.getTx_Currency();
        datosEMV[18]=this.getTx_Room();
        datosEMV[19]=this.getTx_Waiter();
        datosEMV[20]=this.getTx_Shifts();
        datosEMV[21]=this.getTx_Tip();
        datosEMV[22]=this.getTx_OperationNumber();
        datosEMV[23]=this.getTx_Auth();
        datosEMV[24]=this.getTx_Date();
        datosEMV[25]=this.getAvs_Adress();
        datosEMV[26]=this.getAvs_District();
        datosEMV[27]=this.getAvs_Municipality();
        datosEMV[28]=this.getAvs_City();
        datosEMV[29]=this.getAvs_State();
        datosEMV[30]=this.getAvs_zip();
        datosEMV[31]=this.getCc_AMEXCvCsc();
        datosEMV[32]=this.getPModo();
        
        return datosEMV;
    }
    // TRANSACCIONES
    public JClsPinPadTBResponse getVentaDirecta(String DbgSetUrl, String Tx_Amount, String Bs_User, String Bs_Pwd, String Bs_UsrTransaction, String Bs_Company,
                                String Bs_Branch, String Bs_Country, String Cc_Type, String Tx_Merchant, String Tx_Reference, String Tx_OperationType,
                                String Tx_Currency, String Cc_AMEXCvCsc){
        this.setDbgSetUrl(DbgSetUrl);
        this.setTx_Amount(Tx_Amount);
        this.setBs_User(Bs_User);
        this.setBs_Pwd(Bs_Pwd);
        this.setBs_UsrTransaction(Bs_UsrTransaction);
        this.setBs_Company(Bs_Company);
        this.setBs_Branch(Bs_Branch);
        this.setBs_Country(Bs_Country);
        this.setCc_Type(Cc_Type);
        this.setCc_Name(""); this.setCc_Number(""); this.setCc_ExpMonth(""); this.setCc_ExpYear(""); this.setCc_CvvCsc(""); 
        this.setTx_Merchant(Tx_Merchant);
        this.setTx_Reference(Tx_Reference);
        this.setTx_OperationType(Tx_OperationType);
        this.setTx_Currency(Tx_Currency);
        this.setTx_Room(""); this.setTx_Waiter("");  this.setTx_Shifts(""); this.setTx_Tip(""); this.setTx_OperationNumber("");
        this.setTx_Auth(""); this.setTx_Date(""); this.setAvs_Adress(""); this.setAvs_District(""); this.setAvs_Municipality("");
        this.setAvs_City(""); this.setAvs_State(""); this.setAvs_zip("");
        this.setCc_AMEXCvCsc(Cc_AMEXCvCsc);
        this.setPModo("VD");
        
        return emvcls.validarEMVDll(this);
    }
    
    public JClsPinPadTBResponse getLealtad(String DbgSetUrl, String Tx_Amount, String Bs_User, String Bs_Pwd, String Bs_UsrTransaction, String Bs_Company,
                                String Bs_Branch, String Bs_Country, String Cc_Type, String Tx_Merchant, String Tx_Reference, String Tx_OperationType,
                                String Tx_Currency, String Cc_AMEXCvCsc){
        this.setDbgSetUrl(DbgSetUrl);
        this.setTx_Amount(Tx_Amount);
        this.setBs_User(Bs_User);
        this.setBs_Pwd(Bs_Pwd);
        this.setBs_UsrTransaction(Bs_UsrTransaction);
        this.setBs_Company(Bs_Company);
        this.setBs_Branch(Bs_Branch);
        this.setBs_Country(Bs_Country);
        this.setCc_Type(Cc_Type);
        this.setCc_Name(""); this.setCc_Number(""); this.setCc_ExpMonth(""); this.setCc_ExpYear(""); this.setCc_CvvCsc(""); 
        this.setTx_Merchant(Tx_Merchant);
        this.setTx_Reference(Tx_Reference);
        this.setTx_OperationType(Tx_OperationType);
        this.setTx_Currency(Tx_Currency);
        this.setTx_Room(""); this.setTx_Waiter("");  this.setTx_Shifts(""); this.setTx_Tip(""); this.setTx_OperationNumber("");
        this.setTx_Auth(""); this.setTx_Date(""); this.setAvs_Adress(""); this.setAvs_District(""); this.setAvs_Municipality("");
        this.setAvs_City(""); this.setAvs_State(""); this.setAvs_zip("");
        this.setCc_AMEXCvCsc(Cc_AMEXCvCsc);
        this.setPModo("SL");
        
        return emvcls.validarEMVDll(this);
    }    
    
    public JClsPinPadTBResponse getVentaDirectaMOTO(String DbgSetUrl, String Tx_Amount, String Bs_User, String Bs_Pwd, String Bs_UsrTransaction, String Bs_Company,
                                String Bs_Branch, String Bs_Country, String Cc_Type, String Cc_Name, String Cc_Number, String Cc_ExpMonth,
                                String Cc_ExpYear, String Cc_CvvCsc, String Tx_Merchant, String Tx_Reference, String Tx_OperationType, String Tx_Currency){
        this.setDbgSetUrl(DbgSetUrl);
        this.setTx_Amount(Tx_Amount);
        this.setBs_User(Bs_User);
        this.setBs_Pwd(Bs_Pwd);
        this.setBs_UsrTransaction(Bs_UsrTransaction);
        this.setBs_Company(Bs_Company);
        this.setBs_Branch(Bs_Branch);
        this.setBs_Country(Bs_Country);
        this.setCc_Type(Cc_Type);
        this.setCc_Name(Cc_Name);
        this.setCc_Number(Cc_Number);
        this.setCc_ExpMonth(Cc_ExpMonth);
        this.setCc_ExpYear(Cc_ExpYear);
        this.setCc_CvvCsc(Cc_CvvCsc); 
        this.setTx_Merchant(Tx_Merchant);
        this.setTx_Reference(Tx_Reference);
        this.setTx_OperationType(Tx_OperationType);
        this.setTx_Currency(Tx_Currency);
        this.setTx_Room(""); this.setTx_Waiter("");  this.setTx_Shifts(""); this.setTx_Tip(""); this.setTx_OperationNumber("");
        this.setTx_Auth(""); this.setTx_Date(""); this.setAvs_Adress(""); this.setAvs_District(""); this.setAvs_Municipality("");
        this.setAvs_City(""); this.setAvs_State(""); this.setAvs_zip(""); this.setCc_AMEXCvCsc("");
        this.setPModo("VM");
        
        return emvcls.validarEMVDll(this);
    }
    
    public void setVentaForzadaMOTO(String DbgSetUrl, String Tx_Amount, String Bs_User, String Bs_Pwd, String Bs_UsrTransaction, String Bs_Company,
                                String Bs_Branch, String Bs_Country, String Cc_Type, String Cc_Name, String Cc_Number, String Cc_ExpMonth, String Cc_ExpYear,
                                String Cc_CvvCsc, String Tx_Merchant, String Tx_Reference, String Tx_OperationType, String Tx_Currency, String Tx_Auth){
        this.setDbgSetUrl(DbgSetUrl);
        this.setTx_Amount(Tx_Amount);
        this.setBs_User(Bs_User);
        this.setBs_Pwd(Bs_Pwd);
        this.setBs_UsrTransaction(Bs_UsrTransaction);
        this.setBs_Company(Bs_Company);
        this.setBs_Branch(Bs_Branch);
        this.setBs_Country(Bs_Country);
        this.setCc_Type(Cc_Type);
        this.setCc_Name(Cc_Name);
        this.setCc_Number(Cc_Number);
        this.setCc_ExpMonth(Cc_ExpMonth);
        this.setCc_ExpYear(Cc_ExpYear);
        this.setCc_CvvCsc(Cc_CvvCsc); 
        this.setTx_Merchant(Tx_Merchant);
        this.setTx_Reference(Tx_Reference);
        this.setTx_OperationType(Tx_OperationType);
        this.setTx_Currency(Tx_Currency);
        this.setTx_Room(""); this.setTx_Waiter("");  this.setTx_Shifts(""); this.setTx_Tip(""); this.setTx_OperationNumber("");
        this.setTx_Auth(Tx_Auth);
        this.setTx_Date(""); this.setAvs_Adress(""); this.setAvs_District(""); this.setAvs_Municipality("");
        this.setAvs_City(""); this.setAvs_State(""); this.setAvs_zip(""); this.setCc_AMEXCvCsc("");
        this.setPModo("FM");
    }
    
    public void setVentaAVS(String DbgSetUrl, String Tx_Amount, String Bs_User, String Bs_Pwd, String Bs_UsrTransaction, String Bs_Company,
                            String Bs_Branch, String Bs_Country, String Cc_Type, String Cc_Name, String Cc_Number, String Cc_ExpMonth, String Cc_ExpYear,
                            String Cc_CvvCsc, String Tx_Merchant, String Tx_Reference, String Tx_OperationType, String Tx_Currency,
                            String Avs_Adress, String Avs_District, String Avs_Municipality, String Avs_City, String Avs_State, String Avs_zip){
        this.setDbgSetUrl(DbgSetUrl);
        this.setTx_Amount(Tx_Amount);
        this.setBs_User(Bs_User);
        this.setBs_Pwd(Bs_Pwd);
        this.setBs_UsrTransaction(Bs_UsrTransaction);
        this.setBs_Company(Bs_Company);
        this.setBs_Branch(Bs_Branch);
        this.setBs_Country(Bs_Country);
        this.setCc_Type(Cc_Type);
        this.setCc_Name(Cc_Name);
        this.setCc_Number(Cc_Number);
        this.setCc_ExpMonth(Cc_ExpMonth);
        this.setCc_ExpYear(Cc_ExpYear);
        this.setCc_CvvCsc(Cc_CvvCsc); 
        this.setTx_Merchant(Tx_Merchant);
        this.setTx_Reference(Tx_Reference);
        this.setTx_OperationType(Tx_OperationType);
        this.setTx_Currency(Tx_Currency);
        this.setTx_Room(""); this.setTx_Waiter("");  this.setTx_Shifts(""); this.setTx_Tip(""); this.setTx_OperationNumber("");
        this.setTx_Auth(""); this.setTx_Date("");
        this.setAvs_Adress(Avs_Adress);
        this.setAvs_District(Avs_District);
        this.setAvs_Municipality(Avs_Municipality);
        this.setAvs_City(Avs_City);
        this.setAvs_State(Avs_State);
        this.setAvs_zip(Avs_zip);
        this.setCc_AMEXCvCsc("");
        this.setPModo("AVS");
    }
    
    public JClsPinPadTBResponse getCancelacion(String DbgSetUrl, String Tx_Amount, String Bs_User, String Bs_Pwd, String Bs_UsrTransaction, String Bs_Company,
                                String Bs_Branch, String Bs_Country, String Tx_OperationNumber, String Tx_Auth){
        this.setDbgSetUrl(DbgSetUrl);
        this.setTx_Amount(Tx_Amount);
        this.setBs_User(Bs_User);
        this.setBs_Pwd(Bs_Pwd);
        this.setBs_UsrTransaction(Bs_UsrTransaction);
        this.setBs_Company(Bs_Company);
        this.setBs_Branch(Bs_Branch);
        this.setBs_Country(Bs_Country);
        this.setCc_Type("");
        this.setCc_Name(""); this.setCc_Number(""); this.setCc_ExpMonth(""); this.setCc_ExpYear(""); this.setCc_CvvCsc(""); 
        this.setTx_Merchant(""); this.setTx_Reference(""); this.setTx_OperationType(""); this.setTx_Currency("");
        this.setTx_Room(""); this.setTx_Waiter("");  this.setTx_Shifts(""); this.setTx_Tip("");
        this.setTx_OperationNumber(Tx_OperationNumber);
        this.setTx_Auth(Tx_Auth);
        this.setTx_Date(""); this.setAvs_Adress(""); this.setAvs_District(""); this.setAvs_Municipality("");
        this.setAvs_City(""); this.setAvs_State(""); this.setAvs_zip(""); this.setCc_AMEXCvCsc("");
        this.setPModo("CN");
        
        return emvcls.validarEMVDll(this);
    }
    
    public JClsPinPadTBResponse getReimpresionVoucher(String DbgSetUrl, String Tx_Amount, String Bs_User, String Bs_Pwd, String Bs_UsrTransaction, String Bs_Company,
                                String Bs_Branch, String Bs_Country, String Tx_OperationNumber){
        this.setDbgSetUrl(DbgSetUrl);
        this.setTx_Amount(Tx_Amount);
        this.setBs_User(Bs_User);
        this.setBs_Pwd(Bs_Pwd);
        this.setBs_UsrTransaction(Bs_UsrTransaction);
        this.setBs_Company(Bs_Company);
        this.setBs_Branch(Bs_Branch);
        this.setBs_Country(Bs_Country);
        this.setCc_Type("");
        this.setCc_Name(""); this.setCc_Number(""); this.setCc_ExpMonth(""); this.setCc_ExpYear(""); this.setCc_CvvCsc(""); 
        this.setTx_Merchant(""); this.setTx_Reference(""); this.setTx_OperationType(""); this.setTx_Currency("");
        this.setTx_Room(""); this.setTx_Waiter("");  this.setTx_Shifts(""); this.setTx_Tip("");
        this.setTx_OperationNumber(Tx_OperationNumber);
        this.setTx_Auth("");this.setTx_Date(""); this.setAvs_Adress(""); this.setAvs_District(""); this.setAvs_Municipality("");
        this.setAvs_City(""); this.setAvs_State(""); this.setAvs_zip(""); this.setCc_AMEXCvCsc("");
        this.setPModo("RV");
        
        return emvcls.validarEMVDll(this);
    }
    
    public JClsPinPadTBResponse getConsulta(String DbgSetUrl, String Tx_Amount, String Bs_User, String Bs_Pwd, String Bs_Company,
                                String Bs_Branch, String Tx_Reference, String Tx_Date){
        this.setDbgSetUrl(DbgSetUrl);
        this.setTx_Amount(Tx_Amount);
        this.setBs_User(Bs_User);
        this.setBs_Pwd(Bs_Pwd);
        this.setBs_UsrTransaction("");
        this.setBs_Company(Bs_Company);
        this.setBs_Branch(Bs_Branch);
        this.setBs_Country(""); this.setCc_Type(""); this.setCc_Name(""); this.setCc_Number("");
        this.setCc_ExpMonth(""); this.setCc_ExpYear(""); this.setCc_CvvCsc(""); this.setTx_Merchant("");
        this.setTx_Reference(Tx_Reference);
        this.setTx_OperationType(""); this.setTx_Currency(""); this.setTx_Room(""); this.setTx_Waiter("");
        this.setTx_Shifts(""); this.setTx_Tip(""); this.setTx_OperationNumber(""); this.setTx_Auth("");
        this.setTx_Date(Tx_Date);
        this.setAvs_Adress(""); this.setAvs_District(""); this.setAvs_Municipality("");
        this.setAvs_City(""); this.setAvs_State(""); this.setAvs_zip(""); this.setCc_AMEXCvCsc("");
        this.setPModo("CO");
        
        return emvcls.validarEMVDll(this);
    }
    
    public String getDbgSetUrl() {
        return dbgSetUrl;
    }

    public void setDbgSetUrl(String dbgSetUrl) {
        this.dbgSetUrl = dbgSetUrl;
    }

    public String getTx_Amount() {
        return Tx_Amount;
    }

    public void setTx_Amount(String Tx_Amount) {
        this.Tx_Amount = Tx_Amount;
    }

    public String getBs_User() {
        return Bs_User;
    }

    public void setBs_User(String Bs_User) {
        this.Bs_User = Bs_User;
    }

    public String getBs_Pwd() {
        return Bs_Pwd;
    }

    public void setBs_Pwd(String Bs_Pwd) {
        this.Bs_Pwd = Bs_Pwd;
    }

    public String getBs_UsrTransaction() {
        return Bs_UsrTransaction;
    }

    public void setBs_UsrTransaction(String Bs_UsrTransaction) {
        this.Bs_UsrTransaction = Bs_UsrTransaction;
    }

    public String getBs_Company() {
        return Bs_Company;
    }

    public void setBs_Company(String Bs_Company) {
        this.Bs_Company = Bs_Company;
    }

    public String getBs_Branch() {
        return Bs_Branch;
    }

    public void setBs_Branch(String Bs_Branch) {
        this.Bs_Branch = Bs_Branch;
    }

    public String getBs_Country() {
        return Bs_Country;
    }

    public void setBs_Country(String Bs_Country) {
        this.Bs_Country = Bs_Country;
    }

    public String getCc_Type() {
        return Cc_Type;
    }

    public void setCc_Type(String Cc_Type) {
        this.Cc_Type = Cc_Type;
    }

    public String getCc_Name() {
        return Cc_Name;
    }

    public void setCc_Name(String Cc_Name) {
        this.Cc_Name = Cc_Name;
    }

    public String getCc_Number() {
        return Cc_Number;
    }

    public void setCc_Number(String Cc_Number) {
        this.Cc_Number = Cc_Number;
    }

    public String getCc_ExpMonth() {
        return Cc_ExpMonth;
    }

    public void setCc_ExpMonth(String Cc_ExpMonth) {
        this.Cc_ExpMonth = Cc_ExpMonth;
    }

    public String getCc_ExpYear() {
        return Cc_ExpYear;
    }

    public void setCc_ExpYear(String Cc_ExpYear) {
        this.Cc_ExpYear = Cc_ExpYear;
    }

    public String getCc_CvvCsc() {
        return Cc_CvvCsc;
    }

    public void setCc_CvvCsc(String Cc_CvvCsc) {
        this.Cc_CvvCsc = Cc_CvvCsc;
    }

    public String getTx_Merchant() {
        return Tx_Merchant;
    }

    public void setTx_Merchant(String Tx_Merchant) {
        this.Tx_Merchant = Tx_Merchant;
    }

    public String getTx_Reference() {
        return Tx_Reference;
    }

    public void setTx_Reference(String Tx_Reference) {
        this.Tx_Reference = Tx_Reference;
    }

    public String getTx_OperationType() {
        return Tx_OperationType;
    }

    public void setTx_OperationType(String Tx_OperationType) {
        this.Tx_OperationType = Tx_OperationType;
    }

    public String getTx_Currency() {
        return Tx_Currency;
    }

    public void setTx_Currency(String Tx_Currency) {
        this.Tx_Currency = Tx_Currency;
    }

    public String getTx_Room() {
        return Tx_Room;
    }

    public void setTx_Room(String Tx_Room) {
        this.Tx_Room = Tx_Room;
    }

    public String getTx_Waiter() {
        return Tx_Waiter;
    }

    public void setTx_Waiter(String Tx_Waiter) {
        this.Tx_Waiter = Tx_Waiter;
    }

    public String getTx_Shifts() {
        return Tx_Shifts;
    }

    public void setTx_Shifts(String Tx_Shifts) {
        this.Tx_Shifts = Tx_Shifts;
    }

    public String getTx_Tip() {
        return Tx_Tip;
    }

    public void setTx_Tip(String Tx_Tip) {
        this.Tx_Tip = Tx_Tip;
    }

    public String getTx_OperationNumber() {
        return Tx_OperationNumber;
    }

    public void setTx_OperationNumber(String Tx_OperationNumber) {
        this.Tx_OperationNumber = Tx_OperationNumber;
    }

    public String getTx_Auth() {
        return Tx_Auth;
    }

    public void setTx_Auth(String Tx_Auth) {
        this.Tx_Auth = Tx_Auth;
    }

    public String getTx_Date() {
        return Tx_Date;
    }

    public void setTx_Date(String Tx_Date) {
        this.Tx_Date = Tx_Date;
    }

    public String getAvs_Adress() {
        return Avs_Adress;
    }

    public void setAvs_Adress(String Avs_Adress) {
        this.Avs_Adress = Avs_Adress;
    }

    public String getAvs_District() {
        return Avs_District;
    }

    public void setAvs_District(String Avs_District) {
        this.Avs_District = Avs_District;
    }

    public String getAvs_Municipality() {
        return Avs_Municipality;
    }

    public void setAvs_Municipality(String Avs_Municipality) {
        this.Avs_Municipality = Avs_Municipality;
    }

    public String getAvs_City() {
        return Avs_City;
    }

    public void setAvs_City(String Avs_City) {
        this.Avs_City = Avs_City;
    }

    public String getAvs_State() {
        return Avs_State;
    }

    public void setAvs_State(String Avs_State) {
        this.Avs_State = Avs_State;
    }

    public String getAvs_zip() {
        return Avs_zip;
    }

    public void setAvs_zip(String Avs_zip) {
        this.Avs_zip = Avs_zip;
    }

    public String getCc_AMEXCvCsc() {
        return Cc_AMEXCvCsc;
    }

    public void setCc_AMEXCvCsc(String Cc_AMEXCvCsc) {
        this.Cc_AMEXCvCsc = Cc_AMEXCvCsc;
    }

    public String getPModo() {
        return pModo;
    }

    public void setPModo(String pModo) {
        this.pModo = pModo;
    }    

    public String[] getRequestArray() {
        return request;
    }
    
    public void setRequestArray(String[] request) {
        this.request = request;
    }
    
        // Funcion nativa que llama a la dll puente
    private static native String[] validarEMV(String dbgSetUrl,String Tx_Amount,String Bs_User,String Bs_Pwd,String Bs_UsrTransaction,String Bs_Company,String Bs_Branch,String Bs_Country,String Cc_Type,String Cc_Name,String Cc_Number,
            String Cc_ExpMonth,String Cc_ExpYear,String Cc_CvvCsc,String Tx_Merchant,String Tx_Reference,String Tx_OperationType,String Tx_Currency,String Tx_Room,String Tx_Waiter,String Tx_Shifts,
            String Tx_Tip,String Tx_OperationNumber,String Tx_Auth,String Tx_Date,String Avs_Adress,String Avs_District,String Avs_Municipality,String Avs_City,String Avs_State,String Avs_zip,String Cc_AMEXCvCsc, String pModo);
    static
    {
        System.loadLibrary("xeremvwraper");
    }
}
