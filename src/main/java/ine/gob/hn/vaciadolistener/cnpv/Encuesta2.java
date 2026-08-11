package ine.gob.hn.vaciadolistener.cnpv;

import java.util.List;

public class Encuesta2 {

    public Id id;
    public ViviendaRec VIVIENDA_REC;
    public HogaresRec HOGARES_REC;
    public List<Persona> PERSONAS_REC;
    public MetadatosRec METADATOS_REC;
    public List<MortalidadRec> MORTALIDAD_REC;
    public List<EmigracionRec> EMIGRACION_REC;
    public List<MetadatosDeviceRec> METADATOS_DEVICE_REC;
    public List<MetadatosPublishRecord> METADATOS_PUBLISH_RECORD;
    public List<Visita> VISITA;

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public ViviendaRec getVIVIENDA_REC() {
        return VIVIENDA_REC;
    }

    public void setVIVIENDA_REC(ViviendaRec VIVIENDA_REC) {
        this.VIVIENDA_REC = VIVIENDA_REC;
    }

    public HogaresRec getHOGARES_REC() {
        return HOGARES_REC;
    }

    public void setHOGARES_REC(HogaresRec HOGARES_REC) {
        this.HOGARES_REC = HOGARES_REC;
    }

    public List<Persona> getPERSONAS_REC() {
        return PERSONAS_REC;
    }

    public void setPERSONAS_REC(List<Persona> PERSONAS_REC) {
        this.PERSONAS_REC = PERSONAS_REC;
    }

    public MetadatosRec getMETADATOS_REC() {
        return METADATOS_REC;
    }

    public void setMETADATOS_REC(MetadatosRec METADATOS_REC) {
        this.METADATOS_REC = METADATOS_REC;
    }

    public List<MortalidadRec> getMORTALIDAD_REC() {
        return MORTALIDAD_REC;
    }

    public void setMORTALIDAD_REC(List<MortalidadRec> MORTALIDAD_REC) {
        this.MORTALIDAD_REC = MORTALIDAD_REC;
    }

    public List<EmigracionRec> getEMIGRACION_REC() {
        return EMIGRACION_REC;
    }

    public void setEMIGRACION_REC(List<EmigracionRec> EMIGRACION_REC) {
        this.EMIGRACION_REC = EMIGRACION_REC;
    }

    public List<MetadatosDeviceRec> getMETADATOS_DEVICE_REC() {
        return METADATOS_DEVICE_REC;
    }

    public void setMETADATOS_DEVICE_REC(List<MetadatosDeviceRec> METADATOS_DEVICE_REC) {
        this.METADATOS_DEVICE_REC = METADATOS_DEVICE_REC;
    }

    public List<MetadatosPublishRecord> getMETADATOS_PUBLISH_RECORD() {
        return METADATOS_PUBLISH_RECORD;
    }

    public void setMETADATOS_PUBLISH_RECORD(List<MetadatosPublishRecord> METADATOS_PUBLISH_RECORD) {
        this.METADATOS_PUBLISH_RECORD = METADATOS_PUBLISH_RECORD;
    }

    public List<Visita> getVISITA() {
        return VISITA;
    }

    public void setVISITA(List<Visita> VISITA) {
        this.VISITA = VISITA;
    }

    public static class Id {

        public String L1_DEPARTAMENTO;
        public String L1_MUNICIPIO;
        public String L1_ALDEA;
        public String L1_CASERIO;
        public int L1_APOYO_MUNI;
        public int L1_ZONA;
        public int L1_SECTOR;
        public String L1_SEGMENTO;
        public String L1_COD_ENCUESTADOR;
        public int L1_ESTRUCTURA;
        public int L1_VIVIENDA;
        public int L1_HOGAR;
        public int L1_ID_ASIG_GTI;
    }

    public static class ViviendaRec {

        public int H_TIPO_ESTRUCTURA;
        public int H_VIVE_ESTRUCTURA;
        public int H_V00_TIPO_VIV;
        public int H_V01_TIPO_VIV;
        public int H_V02_PAREDES_VIV;
        public int H_V03_TECHO_VIV;
        public int H_V04_OCUP_VIV;
        public String H_V04_OCUP_OTHER;
        public int H_V05_PISO_VIV;
        public int H_V06_DISPO_AGUA;
        public int H_V07_PROV_AGUA;
        public int H_V08_ACC_ALUM;
        public int H_V09_ELIM_BASURA;
        public int H_V10_PIEZAS_VIV;
        public int H_V11_IDEN_HOGAR;
        public int H_V12_GRU_HOGAR;
    }

    public static class HogaresRec {

        public int H_H01_PIEZA_DORMIR;
        public int H_H02_COCINA_HOG;
        public int H_H03_USO_COCINA;
        public int H_H04_COCINAN_CON;
        public int H_H05_SANIT_HOG;
        public int H_H06_SANI_EXCLU;
        public int H_H07A_REFRI;
        public int H_H07B_ESTUFA;
        public int H_H07C_AIRE;
        public int H_H07D_LAVADORA;
        public int H_H07E_RADIO;
        public int H_H07F_TV;
        public int H_H07G_PC;
        public int H_H07H_TEL_FIJO;
        public int H_H07I_TEL_CEL;
        public int H_H07J_INTERNET;
        public int H_H07K_CABLE;
        public int H_H07L_CARRO;
        public int H_H07M_MOTO;
        public int H_H07N_LANCHA;
        public int H_H08_TEN_VIVI;
        public int H_M01_TOTAL_MUERT;
        public int H_M01_CUANTAS;
        public int H_CH00_NUM_PER;
        public int H_CH06_VERIFICA;
        public int H_E01_NUM_EMI;
        public int H_E06_REME_ANIO;
        public int H_E01_TIEMPO;
        public int H_E07_NO_DEST;
        public String H_VDIRECCION;
        public int H_CH00_NUM_PER_1;
        public int H_H09A_ADULTOS;
        public int H_H09B_ADULTOS;
        public int H_H09C_ADULTOS;
        public int H_H10A_ADULTOS;
        public int H_H10B_ADULTOS;
        public int H_H10C_ADULTOS;
        public int H_HM_DE_18_ANOS;
        public int H_H11A_MENORES;
        public int H_H11B_MENORES;
        public int H_H11C_MENORES;
        public int H_H12A_MENORES;
        public int H_H12B_MENORES;
        public int H_H12C_MENORES;
    }

    public static class Persona {

        public int H_ORDEN;
        public int H_CH000_NPER;
        public String H_CH01_NOMBRE;
        public int H_CH02_PARENTESCO;
        public int H_CH03_SEXO;
        public int H_CH04_EDAD;
        public int H_CH05A_ANIO;
        public int H_CH05B_MES;
        public int H_CH05C_DIA;
        public String H_CH06_IDENTIDAD;
        public int H_P01_PUEBLO;
        public String H_P01_ESPECIFIQUE;
        public String H_P02_LENGUAMAT;
        public String H_P03_LENGUAMAT;
        public String H_P04_ESPANOL;
        public int H_P05A_CAMINAR;
        public int H_P05A_CAMINAR_E;
        public int H_P05A_UD_CAMINAR;
        public int H_P05B_COMUNI_C;
        public int H_P05B_COMUNI;
        public int H_P05B_UD_COMUNI;
        public int H_P05C_UD_VER;
        public int H_P05C_VER_C;
        public int H_P05D_OIR_C;
        public int H_P05D_UD_OIR;
        public int H_P05C_VER;
        public int H_P05D_OIR;
        public int H_P05E_VALERSE;
        public int H_P05E_VALERSE_C;
        public int H_P05E_UD_VALERSE;
        public int H_P05F_RECORDAR;
        public int H_P05F_RECORDAR_C;
        public int H_P05F_UD_RECORDAR;
        public int H_P06_LUGAR_NA;
        public int H_P07_NA_MUNI;
        public int H_P08_NA_PAIS;
        public int H_P09A_LLEGA_ANIO;
        public int H_P09B_LLEGA_MES;
        public int H_P10_LUGAR_RESIDENCIA;
        public int H_P11_MUNI_RESIDENCIA;
        public int H_P12_VIVIA_PAIS;
        public int H_P13_MUNIC_ANIO;
        public int H_P14_RAZON_VINO;
        public int H_P15_INSCRI_RNP;
        public int H_P16_ESTA_CIVIL;
        public int H_P17_ASISTEN_EDU;
        public int H_P18A_NIVEL_EDUC;
        public int H_P18B_GRADO_EDUC;
        public String H_P19_ULTI_CARR;
        public int H_P20_FIN_CARRERA;
        public int H_P21_LEER_ESCRI;
        public int H_P22A_USO_PC;
        public int H_P22B_USO_TABLE;
        public int H_P22C_USO_CEL;
        public int H_P22D_USO_INTER;
        public int H_P23_SEM_PAS_TRA;
        public int H_P24_PRODUCTOS_AGRO;
        public String H_P29_OCUPACION;
        public String H_P30_ESTAB_TRABAJO;
        public int H_P31_LUGAR_TRAB;
        public int H_P31_UGAR_MUNIC;
        public int H_P31_LUGAR_PAIS;
        public int H_P32_TRABAJ_COMO;
        public int H_P33_ACTI_AGRO;
        public int H_P25_ACTI_REALI;
        public int H_P26_BUSC_TRAB;
        public int H_P27_DISPONIBILIDAD;
        public int H_P28_ACT_PRINC;
        public int H_P34_HIJOS_TENIDO;
        public int H_P35_EDAD_EMB;
        public int H_P36_CUANTAS_HIJAS;
        public int H_P37_CUANTOS_HIJOS;
        public int H_P38_TOTAL_VIVOS;
        public int H_P39_TOTAL_HIJAS;
        public int H_P40_TOTAL_HIJOS;
        public int H_P41_ANIO_NAC;
        public int H_P41_MES_NAC;
        public int H_P42_SEXO_ULTIMO;
        public int H_P43_VIVO_ULTIMO;
        public String H_F44_CODIGO_ENEE;
        public int H_ADD_ANOTHER_PERSONA;
        public int H_KEEP_ROW;
    }

    public static class MetadatosRec {

        public String H_START_INTERVIEW_TIME;
        public String H_LATITUD;
        public String H_LONGITUD;
        public String H_END_INTERVIEW_TIME;
        public int H_CONCLUIR_ENTREVISTA;
        public String H_COD_ALD;
        public String H_COD_CASERIO;
        public String H_COD_BARRIO;
        public String H_MANZANA;
        public String H_UR_AREA;
        public String H_CORPRE_MUN_SEGMENTO;
        public String H_TIPO;
        public String H_COD_TIPO;
        public String H_GEOCODIGO;
        public String H_GEOCOD_ESTRUCTURA;
        public String H_LATITUD_N;
        public String H_LONGITUD_N;
        public String H_DISTANCIA;
        public String H_ERROR;
        public String H_SECTOR;
        public String H_ZONA;
    }

    public static class MetadatosDeviceRec {

        public String H_DEVICE_ID;
        public long H_DEVICE_TIMESTAMP;
    }

    public static class MortalidadRec {

        public String H_ORDEN_M;
        public String H_M02_MORT_NOMBRE;
        public String H_M03_MORT_SEXO;
        public String H_M04_MORT_EDAD;
        public String H_M05_MORT_RNP;
        public String H_MM01_MORT_CAUSA;
        public String H_MM02_MORT_EMB;
        public String H_ADD_ANOTHER_MORTALIDAD;
        public String H_M_KEEP_ROW;
    }

    public static class EmigracionRec {

        public String H_ORDEN_E;
        public String H_E01_NOMBRE_EMI;
        public String H_E02_SEXO_EMI;
        public String H_E03_EDAD_EMI;
        public String H_E04_ANIO_EMI;
        public String H_E05_RESI_EMI;
        public String H_ADD_ANOTHER_EMIGRACION;
        public String H_E_KEEP_ROW;

    }

    public static class MetadatosPublishRecord {

        public long H_PROGRAM_PUBLISH_DATE;
        public long H_PROGRAM_PUBLISH_DATE_TIMESTAMP;
    }

    public static class Visita {

        public int H_NVISITA;
        public String H_FVISITA;
        public String H_HVISITA;
        public int H_RVISITA;
        public int H_CONTROL;
        public int H_FIN;
    }
}
