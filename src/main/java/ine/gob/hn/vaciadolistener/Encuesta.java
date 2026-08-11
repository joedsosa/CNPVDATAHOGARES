
package ine.gob.hn.vaciadolistener;

import java.util.List;

public class Encuesta {
    private Id id;
    private SI S_I;
    private SII S_II;
    private List<SIIVIS> S_IIVIS;
    private SIII S_III;
    private SIV S_IV;
    private SV S_V;
    private SVI S_VI;
    private SVII S_VII;
    private SVIII S_VIII;
    private SIX S_IX;
    private List<SX> S_X;
    private List<Modulo> MOD;
    private Control CONTROL;
    private Horas HORAS;
    
    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public SI getS_I() {
        return S_I;
    }

    public void setS_I(SI S_I) {
        this.S_I = S_I;
    }

    public SII getS_II() {
        return S_II;
    }

    public void setS_II(SII S_II) {
        this.S_II = S_II;
    }

    public List<SIIVIS> getS_IIVIS() {
        return S_IIVIS;
    }

    public void setS_IIVIS(List<SIIVIS> S_IIVIS) {
        this.S_IIVIS = S_IIVIS;
    }

    public SIII getS_III() {
        return S_III;
    }

    public void setS_III(SIII S_III) {
        this.S_III = S_III;
    }

    public SIV getS_IV() {
        return S_IV;
    }

    public void setS_IV(SIV S_IV) {
        this.S_IV = S_IV;
    }

    public SV getS_V() {
        return S_V;
    }

    public void setS_V(SV S_V) {
        this.S_V = S_V;
    }

    public SVI getS_VI() {
        return S_VI;
    }

    public void setS_VI(SVI S_VI) {
        this.S_VI = S_VI;
    }

    public SVII getS_VII() {
        return S_VII;
    }

    public void setS_VII(SVII S_VII) {
        this.S_VII = S_VII;
    }

    public SVIII getS_VIII() {
        return S_VIII;
    }

    public void setS_VIII(SVIII S_VIII) {
        this.S_VIII = S_VIII;
    }

    public SIX getS_IX() {
        return S_IX;
    }

    public void setS_IX(SIX S_IX) {
        this.S_IX = S_IX;
    }

    public List<SX> getS_X() {
        return S_X;
    }

    public void setS_X(List<SX> S_X) {
        this.S_X = S_X;
    }

    public List<Modulo> getMOD() {
        return MOD;
    }

    public void setMOD(List<Modulo> MOD) {
        this.MOD = MOD;
    }

    public Control getCONTROL() {
        return CONTROL;
    }

    public void setCONTROL(Control CONTROL) {
        this.CONTROL = CONTROL;
    }

    public Horas getHORAS() {
        return HORAS;
    }

    public void setHORAS(Horas HORAS) {
        this.HORAS = HORAS;
    }
}