# SlideToggleButton
一、自定义控件分为:
1、组合空件：把系统原有的控件组合；
2、继承View类：并且实现onMeasure方法和onDraw方法，其中onLayout方法是不必要实现的，onLayout方法是等到onMeasure方法结束之后，对子View布局摆放，所以View是没有子View的，不需要实现onLayout方法；
3、继承ViewGroup：像LinearLayout、RelativeLayout等都是继承至ViewGroup，我们继承ViewGroup需要实现onMeasure方法和onLayout方法，不需要实现onDraw方法，因为ViewGroup只对子View进行布局摆放，剩下的工作就交给子View，画就交给子View自己做了。

二、（滑动开关），继承View，并实现View.onMeasure(int，int)和onDraw(Canvas)方法：
onMeasure（int，int）方法：测量View的宽高，并且通过setMeasuredDimension(width,height())方法，保存ToggleButtonview的宽和高。
onDraw（Canvas）方法：把ToggelButtonView的内容画出来。
