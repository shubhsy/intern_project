package android.support.v7.app;

import android.support.annotation.Nullable;
import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode.Callback;

public abstract interface AppCompatCallback
{
  public abstract void onSupportActionModeFinished(ActionMode paramActionMode);
  
  public abstract void onSupportActionModeStarted(ActionMode paramActionMode);
  
  @Nullable
  public abstract ActionMode onWindowStartingSupportActionMode(ActionMode.Callback paramCallback);
}


/* Location:           C:\Users\lousy\Desktop\New folder\dex2jar\classes-dex2jar.jar
 * Qualified Name:     android.support.v7.app.AppCompatCallback
 * JD-Core Version:    0.7.0.1
 */