package com.example.finalb_rushadmin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "B-rushDatabase.db";
    private static int DB_VERSION = 6;

    //list of tables in B-rush database
    private static final String TABLE_PERSON = "Person";
    private static final String TABLE_DRIVER = "Driver";
    private static final String TABLE_USER = "User";
    private static final String TABLE_ADMIN = "Admin";
    private static final String TABLE_BUS = "Bus";
    private static final String TABLE_BUS_SCHEDULE = "Bus_Schedule" ;
    private static final String TABLE_BUS_SEAT ="Bus_Seat";
    private static final String TABLE_BUS_STOP = "Bus_Stop";
    private static final String TABLE_GCASH = "Gcash" ;
    private static final String TABLE_PAYMENT = "Payment";
    private static final String  TABLE_TICKET = "Ticket" ;

    //column list of all foreign keys
    private static final String COLUMN_FK_PERSON = "PersonID";
    private static final String COLUMN_FK_DRIVER = "DriverID";
    private static final String COLUMN_FK_ROUTE = "RouteID";
    private static final String COLUMN_FK_SEAT = "SeatID";
    private static final String COLUMN_FK_USER = "UserID";
    private static final String COLUMN_FK_PAYMENT = "PaymentID";
    private static final String COLUMN_FK_BUS = "BusID";

    //list of common columns for all table
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_ADDRESS = "Address";

    //column list of Person table
    private static final String COLUMN_FNAME = "FirstName";
    private static final String COLUMN_MNAME = "MiddleName";
    private static final String COLUMN_LNAME = "LastName";
    private static final String COLUMN_BDAY = "Birthday";
    private static final String COLUMN_CONTACT_NUM = "Contact_Number";

    //column list of User & Admin Table
    private static final String COLUMN_USERNAME = "Username";
    private static final String COLUMN_PASSWORD = "Password";

    //column list of Bus Table
    private static final String COLUMN_PLATE_NUMBER = "Plate_number";

    //column list of Bus Seat
    private static final String COLUMN_SEAT_NUMBER = "Seat_Number";
    private static final String COLUMN_STATUS = "Status";

    //column list of Bus Stop
    private static final String COLUMN_DESTINATION = "Destination";

    //column list of Bus Schedule
    private static final String COLUMN_TIME = "Time";

    //column list of Gcash
    private static final String COLUMN_PHONE_NUMBER = "Phone_number";
    private static final String COLUMN_REFERENCE_NUMBER = "Reference_number";

    //column list of Payments
    private static final String COLUMN_AMOUNT = "Amount";

    //column list of Tickets
    //private static final String COLUMN_SEAT_NUMBER = "Seat Number";
    // private static final String COLUMN_STATUS = "Status";
    private static final String COLUMN_ISCANCELLED = "IsCancelled";

    //Strings to create the tables
    private static final String CREATE_TABLE_PERSON ="CREATE TABLE "+TABLE_PERSON+"("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +COLUMN_FNAME+" TEXT, "
            +COLUMN_MNAME+" TEXT, "+COLUMN_LNAME+" TEXT, "+COLUMN_ADDRESS+" TEXT, "+COLUMN_BDAY+" NUMERIC, "+COLUMN_CONTACT_NUM+" TEXT)";
    private static final String CREATE_TABLE_DRIVER = "CREATE TABLE "+TABLE_DRIVER+" ("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUMN_FK_PERSON+
            " INTEGER,FOREIGN KEY("+COLUMN_FK_PERSON+") REFERENCES "+TABLE_PERSON+"("+COLUMN_ID+"))";
    private static final String CREATE_TABLE_USER = "CREATE TABLE "+TABLE_USER+" ("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUMN_USERNAME+
            " TEXT, "+COLUMN_PASSWORD+ " TEXT, "+COLUMN_FK_PERSON+" INTEGER,FOREIGN KEY("+COLUMN_FK_PERSON+") REFERENCES "+TABLE_PERSON+"("+COLUMN_ID+"))";
    public static final String CREATE_TABLE_ADMIN ="CREATE TABLE "+TABLE_ADMIN+"("+COLUMN_ID+"	INTEGER PRIMARY KEY AUTOINCREMENT,"+COLUMN_FK_PERSON+
            " INTEGER,"+COLUMN_USERNAME+"	TEXT,"+COLUMN_PASSWORD+"	TEXT,FOREIGN KEY("+COLUMN_FK_PERSON+") REFERENCES "+TABLE_PERSON+"("+COLUMN_ID+"))";
    public static final String CREATE_TABLE_BUS ="CREATE TABLE "+TABLE_BUS+" ("+COLUMN_ID+"	INTEGER PRIMARY KEY AUTOINCREMENT,"+COLUMN_FK_DRIVER+
            "	INTEGER ,"+COLUMN_FK_ROUTE+" INTEGER, "+COLUMN_PLATE_NUMBER+" TEXT , FOREIGN KEY("+COLUMN_FK_ROUTE+") REFERENCES "+TABLE_BUS_STOP+
            "("+COLUMN_ID+") , FOREIGN KEY("+COLUMN_FK_DRIVER+") REFERENCES "+TABLE_DRIVER+"("+COLUMN_ID+"))";
    public static final String CREATE_TABLE_BUS_SCHEDULE =" CREATE TABLE "+TABLE_BUS_SCHEDULE+ "( "+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COLUMN_TIME+" INTEGER, "+COLUMN_FK_BUS+" INTEGER ,FOREIGN KEY("+COLUMN_FK_BUS+" )REFERENCES "+TABLE_BUS+"("+COLUMN_ID+"))";
    public static final String CREATE_TABLE_BUS_SEAT="CREATE TABLE "+TABLE_BUS_SEAT+"("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+COLUMN_SEAT_NUMBER+
            "INTEGER,"+COLUMN_STATUS+" TEXT)";
    public static final String CREATE_TABLE_BUS_STOP = "CREATE TABLE "+TABLE_BUS_STOP+"("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COLUMN_DESTINATION+" TEXT)";
    public static final String CREATE_TABLE_GCASH = "CREATE TABLE "+TABLE_GCASH+" ("+COLUMN_ID+"	INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COLUMN_PHONE_NUMBER+ " INTEGER,"+COLUMN_REFERENCE_NUMBER+" INTEGER)";
    public static final String CREATE_TABLE_PAYMENT ="CREATE TABLE "+TABLE_PAYMENT+" ("+COLUMN_ID+"	INTEGER PRIMARY KEY AUTOINCREMENT,"+COLUMN_AMOUNT+"	INTEGER)";
    public static final String CREATE_TABLE_TICKET = "CREATE TABLE "+TABLE_TICKET+" ("+COLUMN_ID+"	INTEGER PRIMARY KEY AUTOINCREMENT,"+COLUMN_FK_USER+" INTEGER,"
            +COLUMN_FK_PAYMENT+" INTEGER,"+COLUMN_FK_SEAT+"	INTEGER,"+COLUMN_SEAT_NUMBER+" INTEGER,"+COLUMN_STATUS+"	TEXT,"+COLUMN_ISCANCELLED+"	Boolean," +
            "FOREIGN KEY("+COLUMN_FK_PAYMENT+") REFERENCES "+TABLE_PAYMENT+"("+COLUMN_ID+"),FOREIGN KEY("+COLUMN_FK_USER+") REFERENCES "+TABLE_USER+
            "("+COLUMN_ID+"),FOREIGN KEY("+COLUMN_FK_SEAT+") REFERENCES " +TABLE_BUS_SEAT+ " ("+COLUMN_ID+"))";

    //methods to use in order to connect to the Database
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PERSON);
        db.execSQL(CREATE_TABLE_DRIVER);
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_BUS);
        db.execSQL(CREATE_TABLE_ADMIN);
        db.execSQL(CREATE_TABLE_BUS_SCHEDULE);
        db.execSQL(CREATE_TABLE_BUS_SEAT);
        db.execSQL(CREATE_TABLE_BUS_STOP);
        db.execSQL(CREATE_TABLE_GCASH );
        db.execSQL(CREATE_TABLE_PAYMENT);
        db.execSQL(CREATE_TABLE_TICKET);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PERSON);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_DRIVER);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_ADMIN);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_BUS);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_BUS_SCHEDULE);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_BUS_SEAT);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_BUS_STOP);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_GCASH );
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PAYMENT);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_TICKET);
        onCreate(db);
    }

    //inserting new data row
    private long insertPerson(String fname, String mname, String lname, String add, String bday, String num){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FNAME, fname);
        contentValues.put(COLUMN_MNAME, mname);
        contentValues.put(COLUMN_LNAME, lname);
        contentValues.put(COLUMN_ADDRESS, add);
        contentValues.put(COLUMN_BDAY, bday);
        contentValues.put(COLUMN_CONTACT_NUM, num);
        long res = db.insert(TABLE_PERSON, null, contentValues);
        return res;
    }
    public boolean insertUser(String fname, String mname, String lname, String add, String bday, String num, String user, String pass) {
        long personID = insertPerson(fname, mname, lname, add, bday, num);
        if (personID == -1) {
            return false;
        } else {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues value = new ContentValues();
            value.put(COLUMN_USERNAME, user);
            value.put(COLUMN_PASSWORD, pass);
            value.put(COLUMN_FK_PERSON, personID);
            long result = db.insert(TABLE_USER, null, value);
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
    }
    public boolean insertBusDriver(String fname, String mname, String lname, String add, String bday, String num) {
        long personID = insertPerson(fname, mname, lname, add, bday, num);
        if (personID == -1) {
            return false;
        } else {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues value = new ContentValues();
            value.put(COLUMN_FK_PERSON, personID);
            long result = db.insert(TABLE_DRIVER, null, value);
            if (result == -1) { return false; } else { return true; }
        }
    }
    private long insertBusStop(String destination){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_DESTINATION, destination);
        long res = db.insert(TABLE_BUS_STOP, null, contentValues);
        return res;
    }
    private long insertBus(long driverID, String destination, String plateNumber){
        long routeID = insertBusStop(destination);
        if(routeID == -1){ return -1; }
        else{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_FK_DRIVER, driverID);
            values.put(COLUMN_FK_ROUTE, routeID);
            values.put(COLUMN_PLATE_NUMBER, plateNumber);
            long res = db.insert(TABLE_BUS, null, values);
            return res;
        }
    }
    public boolean insertBusSchedule(long driverID, String destination, String plateNumber, String time){
        long busID = insertBus(driverID,destination,plateNumber);
        if(busID == -1){ return false; }
        else{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_TIME, time);
            values.put(COLUMN_FK_BUS, busID);
            long res = db.insert(TABLE_BUS_SCHEDULE, null, values);
            return true;
        }
    }
    //returns all rows in the table
    public Cursor getListDrivers(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_DRIVER, null);
        return res;
    }
    public Cursor getListUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_USER, null);
        return res;
    }
    public Cursor getListBusSched(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_BUS_SCHEDULE, null);
        return res;
    }
    //returns a specific row in the database
    public Cursor getPerson(long personID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor person = db.rawQuery("SELECT * FROM "+TABLE_PERSON+" WHERE "+COLUMN_ID+" = "+personID, null);
        if(person != null){ person.moveToFirst(); }
        return person;
    }
    public Cursor getDriver(long driverID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor driver = db.rawQuery("SELECT * FROM "+TABLE_DRIVER+" WHERE "+COLUMN_ID+" = "+driverID, null);
        if(driver != null){ driver.moveToFirst(); }
        return driver;
    }
    public Cursor getUser(long userID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor user = db.rawQuery("SELECT * FROM "+TABLE_USER+" WHERE "+COLUMN_ID+" = "+userID, null);
        if(user != null){ user.moveToFirst(); }
        return user;
    }
    public Cursor getBus(long busID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor bus = db.rawQuery("SELECT * FROM "+TABLE_BUS+" WHERE ID = "+busID, null);
        if(bus != null){ bus.moveToFirst(); }
        return bus;
    }
    public Cursor getRoute(long routeID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor route = db.rawQuery("SELECT * FROM "+TABLE_BUS_STOP+" WHERE ID = "+routeID, null);
        if(route != null){ route.moveToFirst(); }
        return route;
    }
    public Cursor getBusSched(long schedID){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor sched = db.rawQuery("SELECT * FROM "+TABLE_BUS_SCHEDULE+" WHERE ID = "+schedID, null);
        if(sched != null){ sched.moveToFirst(); }
        return sched;
    }
    //updates a specific row in the database
    private boolean updatePerson(String personID, String fname, String mname, String lname, String add, String bday, String num){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FNAME, fname);
        contentValues.put(COLUMN_MNAME, mname);
        contentValues.put(COLUMN_LNAME, lname);
        contentValues.put(COLUMN_ADDRESS, add);
        contentValues.put(COLUMN_BDAY, bday);
        contentValues.put(COLUMN_CONTACT_NUM, num);
        db.update(TABLE_PERSON, contentValues, "ID = ?", new String[] { personID });
        return true;
    }
    public boolean updateDriver(String personID, String fname, String mname, String lname, String add, String bday, String num){
        boolean flag = updatePerson(personID, fname, mname, lname, add, bday, num);
        if(flag){ return true; }
        else { return false; }
    }
    public boolean updateUser(String personID, String fname, String mname, String lname, String add, String bday, String num){
        boolean flag = updatePerson(personID, fname, mname, lname, add, bday, num);
        if(flag){ return true; }
        else { return false; }
    }
    //deletes a specific row in table
    private boolean deletePerson(String personID){
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.delete(TABLE_PERSON, "ID = ?", new String[] { personID });
        if(res == -1){ return false; }
        else{ return true; }
    }
    public boolean deleteDriver(String ID){
        SQLiteDatabase db = this.getWritableDatabase();
        long driverID = Long.parseLong(ID);
        Cursor cursor = getDriver(driverID);
        long tempID = cursor.getLong(cursor.getColumnIndex("PersonID"));
        String personID = String.valueOf(tempID);
        boolean flag = deletePerson(personID);
        if(flag){
            db.delete(TABLE_DRIVER, "ID = ?", new String[] { ID });
            return true;
        }
        else { return false; }
    }
    public boolean deleteUser(String ID){
        SQLiteDatabase db = this.getWritableDatabase();
        long userID = Long.parseLong(ID);
        Cursor cursor = getUser(userID);
        long tempID = cursor.getLong(cursor.getColumnIndex("PersonID"));
        String personID = String.valueOf(tempID);
        boolean flag = deletePerson(personID);
        if(flag){
            db.delete(TABLE_USER, "ID = ?", new String[] { ID });
            return true;
        }
        else { return false; }
    }
    private boolean deleteDestination(String routeID){
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.delete(TABLE_BUS_STOP, "ID = ?", new String[] { routeID });
        if(res == -1){ return false; }
        else{ return true; }
    }
    private boolean deleteBus(String busID){
        SQLiteDatabase db = this.getWritableDatabase();
        long bus_id = Long.parseLong(busID);
        Cursor cursor = getBus(bus_id);
        long tempID = cursor.getLong(cursor.getColumnIndex("RouteID"));
        String routeID = String.valueOf(tempID);
        boolean flag = deleteDestination(routeID);
        if(flag){
            db.delete(TABLE_BUS, "ID = ?", new String[]{ busID });
            return true;
        }
        else{ return false; }
    }
    public boolean deleteBusSched(String ID){
        SQLiteDatabase db = this.getWritableDatabase();
        long schedID = Long.parseLong(ID);
        Cursor cursor = getBusSched(schedID);
        long tempID = cursor.getLong(cursor.getColumnIndex("BusID"));
        String busID = String.valueOf(tempID);
        boolean flag = deleteBus(busID);
        if(flag){
            db.delete(TABLE_BUS_SCHEDULE, "ID = ?", new String[]{ ID });
            return true;
        }
        else{ return false; }
    }
    //checks admin login
    public boolean adminAccountExists(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_ADMIN+" WHERE Username = ? AND Password = ?", new String[]{ username, password });
        if(cursor.getCount() > 0){ return true; }
        else{ return false; }
    }
    //get the List of Drivers for Spinner
    public ArrayList<String> getDriverList(){
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_DRIVER, null);
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                long personID = cursor.getLong(cursor.getColumnIndex("PersonID"));
                String ID = String.valueOf(personID);
                Cursor person = getPerson(personID);
                String name = ID+"-"+person.getString(person.getColumnIndex("FirstName"))+" "+
                        person.getString(person.getColumnIndex("LastName"));
                list.add(name);
            }
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return list;
    }
}
