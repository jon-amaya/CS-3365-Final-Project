/*
    Sets the different options for logging into the system, what type of role there can be.
 */
package hospitalSystem;

public enum Option {
    Staff, Nurse, Doctor, CEO;

    private Option(){}

    public String value(){
        return name();
    }

    public static Option fromvalue(String s){
        return valueOf(s);
    }
}
