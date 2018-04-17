package com.treatmentangel.model;



import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchModel implements Parcelable
{

    @SerializedName("npiid")
    @Expose
    private String npiid;
    @SerializedName("prefix")
    @Expose
    private String prefix;
    @SerializedName("org_name")
    @Expose
    private String orgName;
    @SerializedName("availability")
    @Expose
    private String availability;
    @SerializedName("fname")
    @Expose
    private Object fname;
    @SerializedName("lname")
    @Expose
    private Object lname;
    @SerializedName("profile_image")
    @Expose
    private Object profileImage;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("user_gender")
    @Expose
    private Object userGender;
    @SerializedName("user_about")
    @Expose
    private String userAbout;
    @SerializedName("fax")
    @Expose
    private Object fax;
    @SerializedName("cost")
    @Expose
    private String cost;
    @SerializedName("faith")
    @Expose
    private String faith;
    @SerializedName("sponsore")
    @Expose
    private String sponsore;
    @SerializedName("address1")
    @Expose
    private String address1;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("postal_code")
    @Expose
    private String postalCode;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("distance")
    @Expose
    private String distance;
    public final static Parcelable.Creator<SearchModel> CREATOR = new Creator<SearchModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SearchModel createFromParcel(Parcel in) {
            return new SearchModel(in);
        }

        public SearchModel[] newArray(int size) {
            return (new SearchModel[size]);
        }

    }
            ;

    protected SearchModel(Parcel in) {
        this.npiid = ((String) in.readValue((String.class.getClassLoader())));
        this.prefix = ((String) in.readValue((String.class.getClassLoader())));
        this.orgName = ((String) in.readValue((String.class.getClassLoader())));
        this.availability = ((String) in.readValue((String.class.getClassLoader())));
        this.fname = ((Object) in.readValue((Object.class.getClassLoader())));
        this.lname = ((Object) in.readValue((Object.class.getClassLoader())));
        this.profileImage = ((Object) in.readValue((Object.class.getClassLoader())));
        this.phone = ((String) in.readValue((String.class.getClassLoader())));
        this.userGender = ((Object) in.readValue((Object.class.getClassLoader())));
        this.userAbout = ((String) in.readValue((String.class.getClassLoader())));
        this.fax = ((Object) in.readValue((Object.class.getClassLoader())));
        this.cost = ((String) in.readValue((String.class.getClassLoader())));
        this.faith = ((String) in.readValue((String.class.getClassLoader())));
        this.sponsore = ((String) in.readValue((String.class.getClassLoader())));
        this.address1 = ((String) in.readValue((String.class.getClassLoader())));
        this.city = ((String) in.readValue((String.class.getClassLoader())));
        this.state = ((String) in.readValue((String.class.getClassLoader())));
        this.postalCode = ((String) in.readValue((String.class.getClassLoader())));
        this.latitude = ((String) in.readValue((String.class.getClassLoader())));
        this.longitude = ((String) in.readValue((String.class.getClassLoader())));
        this.distance = ((String) in.readValue((String.class.getClassLoader())));
    }

    public SearchModel() {
    }

    public String getNpiid() {
        return npiid;
    }

    public void setNpiid(String npiid) {
        this.npiid = npiid;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public Object getFname() {
        return fname;
    }

    public void setFname(Object fname) {
        this.fname = fname;
    }

    public Object getLname() {
        return lname;
    }

    public void setLname(Object lname) {
        this.lname = lname;
    }

    public Object getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Object profileImage) {
        this.profileImage = profileImage;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Object getUserGender() {
        return userGender;
    }

    public void setUserGender(Object userGender) {
        this.userGender = userGender;
    }

    public String getUserAbout() {
        return userAbout;
    }

    public void setUserAbout(String userAbout) {
        this.userAbout = userAbout;
    }

    public Object getFax() {
        return fax;
    }

    public void setFax(Object fax) {
        this.fax = fax;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getFaith() {
        return faith;
    }

    public void setFaith(String faith) {
        this.faith = faith;
    }

    public String getSponsore() {
        return sponsore;
    }

    public void setSponsore(String sponsore) {
        this.sponsore = sponsore;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(npiid);
        dest.writeValue(prefix);
        dest.writeValue(orgName);
        dest.writeValue(availability);
        dest.writeValue(fname);
        dest.writeValue(lname);
        dest.writeValue(profileImage);
        dest.writeValue(phone);
        dest.writeValue(userGender);
        dest.writeValue(userAbout);
        dest.writeValue(fax);
        dest.writeValue(cost);
        dest.writeValue(faith);
        dest.writeValue(sponsore);
        dest.writeValue(address1);
        dest.writeValue(city);
        dest.writeValue(state);
        dest.writeValue(postalCode);
        dest.writeValue(latitude);
        dest.writeValue(longitude);
        dest.writeValue(distance);
    }

    public int describeContents() {
        return 0;
    }

}