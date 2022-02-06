/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author imunoz
 */
@Entity
public class BudgetDataTbl implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String BUDGET_NAME;

    private String PERIOD_NAME;
    private String AMOUNT_TYPE;
    private String EMPRESA;
    private String SUBCUENTA;
    private float BUDGET;
    private float ENCUMBRANCE;
    private float ACTUAL;
    private float FUNDS_AVAILABLE;
    private float REQ_ENCUMBRANCE_AMOUNT;
    private float PO_ENCUMBRANCE_AMOUNT;
    private float OTHER_ENCUMBRANCE_AMOUNT;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (BUDGET_NAME != null ? BUDGET_NAME.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BudgetDataTbl)) {
            return false;
        }
        BudgetDataTbl other = (BudgetDataTbl) object;
        if ((this.BUDGET_NAME == null && other.BUDGET_NAME != null) || (this.BUDGET_NAME != null && !this.BUDGET_NAME.equals(other.BUDGET_NAME))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.BudgetDataTbl[ BUDGET_NAME=" + BUDGET_NAME + " ]";
    }

    public String getBUDGET_NAME() {
        return BUDGET_NAME;
    }

    public void setBUDGET_NAME(String BUDGET_NAME) {
        this.BUDGET_NAME = BUDGET_NAME;
    }

    public String getPERIOD_NAME() {
        return PERIOD_NAME;
    }

    public void setPERIOD_NAME(String PERIOD_NAME) {
        this.PERIOD_NAME = PERIOD_NAME;
    }

    public String getAMOUNT_TYPE() {
        return AMOUNT_TYPE;
    }

    public void setAMOUNT_TYPE(String AMOUNT_TYPE) {
        this.AMOUNT_TYPE = AMOUNT_TYPE;
    }

    public String getEMPRESA() {
        return EMPRESA;
    }

    public void setEMPRESA(String EMPRESA) {
        this.EMPRESA = EMPRESA;
    }

    public String getSUBCUENTA() {
        return SUBCUENTA;
    }

    public void setSUBCUENTA(String SUBCUENTA) {
        this.SUBCUENTA = SUBCUENTA;
    }

    public float getBUDGET() {
        return BUDGET;
    }

    public void setBUDGET(float BUDGET) {
        this.BUDGET = BUDGET;
    }

    public float getENCUMBRANCE() {
        return ENCUMBRANCE;
    }

    public void setENCUMBRANCE(float ENCUMBRANCE) {
        this.ENCUMBRANCE = ENCUMBRANCE;
    }

    public float getACTUAL() {
        return ACTUAL;
    }

    public void setACTUAL(float ACTUAL) {
        this.ACTUAL = ACTUAL;
    }

    public float getFUNDS_AVAILABLE() {
        return FUNDS_AVAILABLE;
    }

    public void setFUNDS_AVAILABLE(float FUNDS_AVAILABLE) {
        this.FUNDS_AVAILABLE = FUNDS_AVAILABLE;
    }

    public float getREQ_ENCUMBRANCE_AMOUNT() {
        return REQ_ENCUMBRANCE_AMOUNT;
    }

    public void setREQ_ENCUMBRANCE_AMOUNT(float REQ_ENCUMBRANCE_AMOUNT) {
        this.REQ_ENCUMBRANCE_AMOUNT = REQ_ENCUMBRANCE_AMOUNT;
    }

    public float getPO_ENCUMBRANCE_AMOUNT() {
        return PO_ENCUMBRANCE_AMOUNT;
    }

    public void setPO_ENCUMBRANCE_AMOUNT(float PO_ENCUMBRANCE_AMOUNT) {
        this.PO_ENCUMBRANCE_AMOUNT = PO_ENCUMBRANCE_AMOUNT;
    }

    public float getOTHER_ENCUMBRANCE_AMOUNT() {
        return OTHER_ENCUMBRANCE_AMOUNT;
    }

    public void setOTHER_ENCUMBRANCE_AMOUNT(float OTHER_ENCUMBRANCE_AMOUNT) {
        this.OTHER_ENCUMBRANCE_AMOUNT = OTHER_ENCUMBRANCE_AMOUNT;
    }
    
}
