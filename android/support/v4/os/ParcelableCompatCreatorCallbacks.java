package android.support.v4.os;

import android.os.Parcel;

@Deprecated
public abstract interface ParcelableCompatCreatorCallbacks<T>
{
  public abstract T createFromParcel(Parcel paramParcel, ClassLoader paramClassLoader);
  
  public abstract T[] newArray(int paramInt);
}


/* Location:           C:\Users\lousy\Desktop\New folder\dex2jar\classes-dex2jar.jar
 * Qualified Name:     android.support.v4.os.ParcelableCompatCreatorCallbacks
 * JD-Core Version:    0.7.0.1
 */