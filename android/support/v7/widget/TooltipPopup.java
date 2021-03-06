package android.support.v7.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.annotation.RestrictTo;
import android.support.v7.appcompat.R.dimen;
import android.support.v7.appcompat.R.id;
import android.support.v7.appcompat.R.layout;
import android.support.v7.appcompat.R.style;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
class TooltipPopup
{
  private static final String TAG = "TooltipPopup";
  private final View mContentView;
  private final Context mContext;
  private final WindowManager.LayoutParams mLayoutParams = new WindowManager.LayoutParams();
  private final TextView mMessageView;
  private final int[] mTmpAnchorPos = new int[2];
  private final int[] mTmpAppPos = new int[2];
  private final Rect mTmpDisplayFrame = new Rect();
  
  TooltipPopup(Context paramContext)
  {
    this.mContext = paramContext;
    this.mContentView = LayoutInflater.from(this.mContext).inflate(R.layout.tooltip, null);
    this.mMessageView = ((TextView)this.mContentView.findViewById(R.id.message));
    this.mLayoutParams.setTitle(getClass().getSimpleName());
    this.mLayoutParams.packageName = this.mContext.getPackageName();
    this.mLayoutParams.type = 1002;
    this.mLayoutParams.width = -2;
    this.mLayoutParams.height = -2;
    this.mLayoutParams.format = -3;
    this.mLayoutParams.windowAnimations = R.style.Animation_AppCompat_Tooltip;
    this.mLayoutParams.flags = 24;
  }
  
  private void computePosition(View paramView, int paramInt1, int paramInt2, boolean paramBoolean, WindowManager.LayoutParams paramLayoutParams)
  {
    int i = this.mContext.getResources().getDimensionPixelOffset(R.dimen.tooltip_precise_anchor_threshold);
    label67:
    Object localObject1;
    if (paramView.getWidth() >= i)
    {
      if (paramView.getHeight() < i) {
        break label132;
      }
      j = this.mContext.getResources().getDimensionPixelOffset(R.dimen.tooltip_precise_anchor_extra_offset);
      i = paramInt2 + j;
      j = paramInt2 - j;
      paramInt2 = i;
      i = j;
      paramLayoutParams.gravity = 49;
      localObject1 = this.mContext.getResources();
      if (!paramBoolean) {
        break label143;
      }
    }
    int k;
    label132:
    label143:
    for (int j = R.dimen.tooltip_y_offset_touch;; j = R.dimen.tooltip_y_offset_non_touch)
    {
      k = ((Resources)localObject1).getDimensionPixelOffset(j);
      localObject1 = getAppRootView(paramView);
      if (localObject1 != null) {
        break label151;
      }
      Log.e("TooltipPopup", "Cannot find app view");
      return;
      paramInt1 = paramView.getWidth() / 2;
      break;
      paramInt2 = paramView.getHeight();
      i = 0;
      break label67;
    }
    label151:
    ((View)localObject1).getWindowVisibleDisplayFrame(this.mTmpDisplayFrame);
    Object localObject2;
    if ((this.mTmpDisplayFrame.left < 0) && (this.mTmpDisplayFrame.top < 0))
    {
      localObject2 = this.mContext.getResources();
      j = ((Resources)localObject2).getIdentifier("status_bar_height", "dimen", "android");
      if (j == 0) {
        break label388;
      }
    }
    label388:
    for (j = ((Resources)localObject2).getDimensionPixelSize(j);; j = 0)
    {
      localObject2 = ((Resources)localObject2).getDisplayMetrics();
      this.mTmpDisplayFrame.set(0, j, ((DisplayMetrics)localObject2).widthPixels, ((DisplayMetrics)localObject2).heightPixels);
      ((View)localObject1).getLocationOnScreen(this.mTmpAppPos);
      paramView.getLocationOnScreen(this.mTmpAnchorPos);
      paramView = this.mTmpAnchorPos;
      paramView[0] -= this.mTmpAppPos[0];
      paramView = this.mTmpAnchorPos;
      paramView[1] -= this.mTmpAppPos[1];
      paramLayoutParams.x = (this.mTmpAnchorPos[0] + paramInt1 - this.mTmpDisplayFrame.width() / 2);
      paramInt1 = View.MeasureSpec.makeMeasureSpec(0, 0);
      this.mContentView.measure(paramInt1, paramInt1);
      paramInt1 = this.mContentView.getMeasuredHeight();
      i = this.mTmpAnchorPos[1] + i - k - paramInt1;
      paramInt2 = this.mTmpAnchorPos[1] + paramInt2 + k;
      if (!paramBoolean) {
        break label401;
      }
      if (i < 0) {
        break;
      }
      paramLayoutParams.y = i;
      return;
    }
    paramLayoutParams.y = paramInt2;
    return;
    label401:
    if (paramInt2 + paramInt1 <= this.mTmpDisplayFrame.height())
    {
      paramLayoutParams.y = paramInt2;
      return;
    }
    paramLayoutParams.y = i;
  }
  
  private static View getAppRootView(View paramView)
  {
    for (Context localContext = paramView.getContext(); (localContext instanceof ContextWrapper); localContext = ((ContextWrapper)localContext).getBaseContext()) {
      if ((localContext instanceof Activity)) {
        return ((Activity)localContext).getWindow().getDecorView();
      }
    }
    return paramView.getRootView();
  }
  
  void hide()
  {
    if (!isShowing()) {
      return;
    }
    ((WindowManager)this.mContext.getSystemService("window")).removeView(this.mContentView);
  }
  
  boolean isShowing()
  {
    return this.mContentView.getParent() != null;
  }
  
  void show(View paramView, int paramInt1, int paramInt2, boolean paramBoolean, CharSequence paramCharSequence)
  {
    if (isShowing()) {
      hide();
    }
    this.mMessageView.setText(paramCharSequence);
    computePosition(paramView, paramInt1, paramInt2, paramBoolean, this.mLayoutParams);
    ((WindowManager)this.mContext.getSystemService("window")).addView(this.mContentView, this.mLayoutParams);
  }
}


/* Location:           C:\Users\lousy\Desktop\New folder\dex2jar\classes-dex2jar.jar
 * Qualified Name:     android.support.v7.widget.TooltipPopup
 * JD-Core Version:    0.7.0.1
 */