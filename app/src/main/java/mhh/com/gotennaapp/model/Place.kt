package mhh.com.gotennaapp.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "places")
class Place(@PrimaryKey
            @ColumnInfo(name = "id")
            @SerializedName("id")
            val id: Int,
            @SerializedName("name")
            @ColumnInfo(name = "name")
            val name: String,
            @SerializedName("latitude")
            @ColumnInfo(name = "latitude")
            val latitude: Float,
            @SerializedName("longitude")
            @ColumnInfo(name = "longitude")
            val longitude: Float,
            @SerializedName("description")
            @ColumnInfo(name = "description")
            val description: String) : Parcelable {


    constructor(parcel: Parcel) : this(
            id = parcel.readInt(),
            name = parcel.readString(),
            latitude = parcel.readFloat(),
            longitude = parcel.readFloat(),
            description = parcel.readString()
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeFloat(latitude)
        parcel.writeFloat(longitude)
        parcel.writeString(description)
    }

    override fun toString(): String {
        return ("Place{" + "id='" + id + '\''.toString() + ", name='" + name + '\''.toString() + ", longitude='"
                + longitude + ", latitude='" + latitude + ", description='" + description + '\''.toString() + '}'.toString())
    }

    companion object CREATOR : Parcelable.Creator<Place> {
        override fun createFromParcel(parcel: Parcel): Place {
            return Place(parcel)
        }

        override fun newArray(size: Int): Array<Place?> {
            return arrayOfNulls(size)
        }

    }
}

