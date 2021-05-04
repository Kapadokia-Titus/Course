package kapadokia.nyandoro.course.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Units implements Parcelable {

    private String id;
    private String course;
    private String unit;
    private String semester;
    private String content;

    public Units() {
    }

    public Units(String id, String course, String unit, String semester, String content) {
        this.id = id;
        this.course = course;
        this.unit = unit;
        this.semester = semester;
        this.content = content;
    }

    protected Units(Parcel in) {
        id = in.readString();
        course = in.readString();
        unit = in.readString();
        semester = in.readString();
        content = in.readString();
    }

    public static final Creator<Units> CREATOR = new Creator<Units>() {
        @Override
        public Units createFromParcel(Parcel in) {
            return new Units(in);
        }

        @Override
        public Units[] newArray(int size) {
            return new Units[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(course);
        parcel.writeString(unit);
        parcel.writeString(semester);
        parcel.writeString(content);
    }
}
