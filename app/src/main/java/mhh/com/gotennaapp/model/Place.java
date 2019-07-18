package mhh.com.gotennaapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "places") public class Place implements Parcelable {

    @PrimaryKey @NonNull @ColumnInfo(name = "id") @SerializedName("id") private int id;
    @SerializedName("name") @ColumnInfo(name = "name") private String name;
    @SerializedName("latitude") @ColumnInfo(name = "latitude") private float latitude;
    @SerializedName("longitude") @ColumnInfo(name = "longitude") private float longitude;
    @SerializedName("description") @ColumnInfo(name = "description") private String description;

    public Place(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.latitude = in.readFloat();
        this.longitude = in.readFloat();
        this.description = in.readString();
    }

    public Place(int id, String name, float latitude, float longitude, String description) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        public Place[] newArray(int size) {
            return new Place[size];
        }
    };

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.name);
        parcel.writeFloat(this.latitude);
        parcel.writeFloat(this.longitude);
        parcel.writeString(this.description);
    }

    @Override public String toString() {
        return "Place{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", longitude='"
            + longitude + ", latitude='" + latitude + ", description='" + description + '\'' + '}';
    }
}

