package filesUtils;

/**
 * 2015 sville
 * PsPcmTxt.java 2015-03-20
 *
 * filesUtils
 */

/**
 * @author sville
 * 
 */
public class PsPcmTxt {

    /**
     * Class constructor.
     * 
     * @param objectid1
     * @param objectvalue1
     * @param objectid2
     * @param objectvalue2
     * @param objectid3
     * @param objectvalue3
     * @param objectid4
     * @param objectvalue4
     * @param objectid5
     * @param objectvalue5
     * @param objectid6
     * @param objectvalue6
     * @param objectid7
     * @param objectvalue7
     * @param progSeq
     * @param hash_signature
     * @param pcText
     * @param pcText2
     * @param hash_signature2
     * @param progSeq2
     */
    public PsPcmTxt(int objectid1, String objectvalue1, int objectid2,
	    String objectvalue2, int objectid3, String objectvalue3,
	    int objectid4, String objectvalue4, int objectid5,
	    String objectvalue5, int objectid6, String objectvalue6,
	    int objectid7, String objectvalue7, long progSeq,
	    String hash_signature, Object pcText) {
	super();
	this.setObjectid1(objectid1);
	this.setObjectid2(objectid2);
	this.setObjectid3(objectid3);
	this.setObjectid4(objectid4);
	this.setObjectid5(objectid5);
	this.setObjectid6(objectid6);
	this.setObjectid7(objectid7);
	this.setObjectvalue1(objectvalue1);
	this.setObjectvalue2(objectvalue2);
	this.setObjectvalue3(objectvalue3);
	this.setObjectvalue4(objectvalue4);
	this.setObjectvalue5(objectvalue5);
	this.setObjectvalue6(objectvalue6);
	this.setObjectvalue7(objectvalue7);
	this.setProgSeq(progSeq);
	this.setPcText(pcText);
	this.setHash_signature(hash_signature);
    }

    /**
     * Class constructor.
     * 
     * @param none
     */
    public PsPcmTxt() {
	// TODO Auto-generated constructor stub
    }

    // class attributes
    private int objectid1;
    private String objectvalue1;
    private int objectid2;
    private String objectvalue2;
    private int objectid3;
    private String objectvalue3;
    private int objectid4;
    private String objectvalue4;
    private int objectid5;
    private String objectvalue5;
    private int objectid6;
    private String objectvalue6;
    private int objectid7;
    private String objectvalue7;
    private long progSeq;
    private String hash_signature;
    private Object pcText;

    /**
     * @return the objectid1
     */
    public int getObjectid1() {
	return objectid1;
    }

    /**
     * @param objectid1
     *            the objectid1 to set
     */
    public void setObjectid1(int objectid1) {
	this.objectid1 = objectid1;
    }

    /**
     * @return the objectvalue1
     */
    public String getObjectvalue1() {
	return objectvalue1;
    }

    /**
     * @param objectvalue1
     *            the objectvalue1 to set
     */
    public void setObjectvalue1(String objectvalue1) {
	this.objectvalue1 = objectvalue1;
    }

    /**
     * @return the objectid2
     */
    public int getObjectid2() {
	return objectid2;
    }

    /**
     * @param objectid2
     *            the objectid2 to set
     */
    public void setObjectid2(int objectid2) {
	this.objectid2 = objectid2;
    }

    /**
     * @return the objectvalue2
     */
    public String getObjectvalue2() {
	return objectvalue2;
    }

    /**
     * @param objectvalue2
     *            the objectvalue2 to set
     */
    public void setObjectvalue2(String objectvalue2) {
	this.objectvalue2 = objectvalue2;
    }

    /**
     * @return the objectid3
     */
    public int getObjectid3() {
	return objectid3;
    }

    /**
     * @param objectid3
     *            the objectid3 to set
     */
    public void setObjectid3(int objectid3) {
	this.objectid3 = objectid3;
    }

    /**
     * @return the objectvalue3
     */
    public String getObjectvalue3() {
	return objectvalue3;
    }

    /**
     * @param objectvalue3
     *            the objectvalue3 to set
     */
    public void setObjectvalue3(String objectvalue3) {
	this.objectvalue3 = objectvalue3;
    }

    /**
     * @return the objectid4
     */
    public int getObjectid4() {
	return objectid4;
    }

    /**
     * @param objectid4
     *            the objectid4 to set
     */
    public void setObjectid4(int objectid4) {
	this.objectid4 = objectid4;
    }

    /**
     * @return the objectvalue4
     */
    public String getObjectvalue4() {
	return objectvalue4;
    }

    /**
     * @param objectvalue4
     *            the objectvalue4 to set
     */
    public void setObjectvalue4(String objectvalue4) {
	this.objectvalue4 = objectvalue4;
    }

    /**
     * @return the objectid5
     */
    public int getObjectid5() {
	return objectid5;
    }

    /**
     * @param objectid5
     *            the objectid5 to set
     */
    public void setObjectid5(int objectid5) {
	this.objectid5 = objectid5;
    }

    /**
     * @return the objectvalue5
     */
    public String getObjectvalue5() {
	return objectvalue5;
    }

    /**
     * @param objectvalue5
     *            the objectvalue5 to set
     */
    public void setObjectvalue5(String objectvalue5) {
	this.objectvalue5 = objectvalue5;
    }

    /**
     * @return the objectid6
     */
    public int getObjectid6() {
	return objectid6;
    }

    /**
     * @param objectid6
     *            the objectid6 to set
     */
    public void setObjectid6(int objectid6) {
	this.objectid6 = objectid6;
    }

    /**
     * @return the objectvalue6
     */
    public String getObjectvalue6() {
	return objectvalue6;
    }

    /**
     * @param objectvalue6
     *            the objectvalue6 to set
     */
    public void setObjectvalue6(String objectvalue6) {
	this.objectvalue6 = objectvalue6;
    }

    /**
     * @return the objectid7
     */
    public int getObjectid7() {
	return objectid7;
    }

    /**
     * @param objectid7
     *            the objectid7 to set
     */
    public void setObjectid7(int objectid7) {
	this.objectid7 = objectid7;
    }

    /**
     * @return the objectvalue7
     */
    public String getObjectvalue7() {
	return objectvalue7;
    }

    /**
     * @param objectvalue7
     *            the objectvalue7 to set
     */
    public void setObjectvalue7(String objectvalue7) {
	this.objectvalue7 = objectvalue7;
    }

    /**
     * @return the progSeq
     */
    public long getProgSeq() {
	return progSeq;
    }

    /**
     * @param progSeq
     *            the progSeq to set
     */
    public void setProgSeq(long progSeq) {
	this.progSeq = progSeq;
    }

    /**
     * @return the hash_signature
     */
    public String getHash_signature() {
	return hash_signature;
    }

    /**
     * @param hash_signature
     *            the hash_signature to set
     */
    public void setHash_signature(String hash_signature) {
	this.hash_signature = hash_signature;
    }

    /**
     * @return the pcText
     */
    public Object getPcText() {
	return pcText;
    }

    /**
     * @param pcText
     *            the pcText to set
     */
    public void setPcText(Object pcText) {
	this.pcText = pcText;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "PsPcmTxt [objectid1=" + objectid1 + ", objectvalue1="
		+ objectvalue1 + ", objectid2=" + objectid2 + ", objectvalue2="
		+ objectvalue2 + ", objectid3=" + objectid3 + ", objectvalue3="
		+ objectvalue3 + ", objectid4=" + objectid4 + ", objectvalue4="
		+ objectvalue4 + ", objectid5=" + objectid5 + ", objectvalue5="
		+ objectvalue5 + ", objectid6=" + objectid6 + ", objectvalue6="
		+ objectvalue6 + ", objectid7=" + objectid7 + ", objectvalue7="
		+ objectvalue7 + ", progSeq=" + progSeq + ", hash_signature="
		+ hash_signature + ", pcText=" + pcText + "]";
    }

}