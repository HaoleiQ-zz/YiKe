package qo.shaoyou.free.qo.ohter;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import qo.shaoyou.free.qo.R;
import qo.shaoyou.free.qo.activity.Main;


public class ListViewCompat extends ListView implements OnScrollListener {

    private static final String TAG = "ListViewCompat";

    private SlideView mFocusedItemView;
    // 区分当前操作是刷新还是加载
    public static final int REFRESH = 0;
    public static final int LOAD = 1;

    // 区分PULL和RELEASE的距离的大小
    private static final int SPACE = 20;

    // 定义header的四种状态和当前状态
    private static final int NONE = 0;
    private static final int PULL = 1;
    private static final int RELEASE = 2;
    private static final int REFRESHING = 3;
    private int state;

    private LayoutInflater inflater;
    private View header;
    private View footer;
    private TextView tip;
    private TextView lastUpdate;
    private ImageView arrow;
    private ProgressBar refreshing;

    private TextView noData;
    private TextView loadFull;
    private TextView more;
    private ProgressBar loading;

    private RotateAnimation animation;
    private RotateAnimation reverseAnimation;

    private int startY;

    private int firstVisibleItem;
    private int scrollState;
    private int headerContentInitialHeight;
    private int headerContentHeight;

    // 只有在listview第一个item显示的时候（listview滑到了顶部）才进行下拉刷新， 否则此时的下拉只是滑动listview
    private boolean isRecorded;
    private boolean isLoading;// 判断是否正在加载
    private boolean loadEnable = true;// 开启或者关闭加载更多功能
    private boolean isLoadFull;
    private int pageSize = 10;

    private OnRefreshListener onRefreshListener;
    private OnLoadListener onLoadListener;

    public ListViewCompat(Context context) {
        super(context);
        initView(context);
    }

    public ListViewCompat(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ListViewCompat(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    public void shrinkListItem(int position) {
        View item = getChildAt(position);

        if (item != null) {
            try {
                ((SlideView) item).shrink();
            } catch (ClassCastException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                int x = (int) event.getX();
                int y = (int) event.getY();
                int position = pointToPosition(x, y);
                Log.e(TAG, "postion=" + position);
                if (position != INVALID_POSITION) {
                    Main.MessageItem data = (Main.MessageItem) getItemAtPosition(position);
                    mFocusedItemView = data.slideView;
                    Log.e(TAG, "FocusedItemView=" + mFocusedItemView);
                }
            if (firstVisibleItem == 0) {
// 				isRecorded = true;
// 				startY = (int) event.getY();
 			}
            }
            break;
//            case MotionEvent.ACTION_CANCEL:
//                break;
//            case MotionEvent.ACTION_UP:
//                if (state == PULL) {
//                    state = NONE;
//                    refreshHeaderViewByState();
//                } else if (state == RELEASE) {
//                    state = REFRESHING;
//                    refreshHeaderViewByState();
//                    onRefresh();
//                }
//                isRecorded = false;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                whenMove(event);
//                break;
//            default:
//                break;
     }

        if (mFocusedItemView != null) {
            mFocusedItemView.onRequireTouchEvent(event);
        }

        return super.onTouchEvent(event);
    }

    // 下拉刷新监听
//    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
//        this.onRefreshListener = onRefreshListener;
//    }

    // 加载更多监听
//    public void setOnLoadListener(OnLoadListener onLoadListener) {
//        this.loadEnable = true;
//        this.onLoadListener = onLoadListener;
//    }

//    public boolean isLoadEnable() {
//        return loadEnable;
//    }
//
//    // 这里的开启或者关闭加载更多，并不支持动态调整
//    public void setLoadEnable(boolean loadEnable) {
//        this.loadEnable = loadEnable;
//        this.removeFooterView(footer);
//    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    // 初始化组件
    private void initView(Context context) {

        // 设置箭头特效
        animation = new RotateAnimation(0, -180,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(100);
        animation.setFillAfter(true);

        reverseAnimation = new RotateAnimation(-180, 0,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        reverseAnimation.setInterpolator(new LinearInterpolator());
        reverseAnimation.setDuration(100);
        reverseAnimation.setFillAfter(true);

        inflater = LayoutInflater.from(context);
        footer = inflater.inflate(R.layout.listview_footer, null);
        loadFull = (TextView) footer.findViewById(R.id.loadFull);
        noData = (TextView) footer.findViewById(R.id.noData);
        more = (TextView) footer.findViewById(R.id.more);
        loading = (ProgressBar) footer.findViewById(R.id.loading);

        header = inflater.inflate(R.layout.pull_to_refresh_header, null);
        arrow = (ImageView) header.findViewById(R.id.arrow);
        tip = (TextView) header.findViewById(R.id.tip);
        lastUpdate = (TextView) header.findViewById(R.id.lastUpdate);
        refreshing = (ProgressBar) header.findViewById(R.id.refreshing);

        // 为listview添加头部和尾部，并进行初始化
        headerContentInitialHeight = header.getPaddingTop();
        measureView(header);
        headerContentHeight = header.getMeasuredHeight();
        topPadding(-headerContentHeight);
        this.addHeaderView(header);
        this.addFooterView(footer);
        this.setOnScrollListener(this);
    }

    public void onRefresh() {
        if (onRefreshListener != null) {
            onRefreshListener.onRefresh();
        }
    }

    public void onLoad() {
        if (onLoadListener != null) {
            onLoadListener.onLoad();
        }
    }

//    public void onRefreshComplete(String updateTime) {
//        lastUpdate.setText(this.getContext().getString(R.string.lastUpdateTime,
//                Utils.getCurrentTime()));
//        state = NONE;
//        refreshHeaderViewByState();
//    }

//    // 用于下拉刷新结束后的回调
//    public void onRefreshComplete() {
//        String currentTime = Utils.getCurrentTime();
//        onRefreshComplete(currentTime);
//    }
//
//    // 用于加载更多结束后的回调
//    public void onLoadComplete() {
//        isLoading = false;
//    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        this.firstVisibleItem = firstVisibleItem;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        this.scrollState = scrollState;
        ifNeedLoad(view, scrollState);
    }

    // 根据listview滑动的状态判断是否需要加载更多
    private void ifNeedLoad(AbsListView view, int scrollState) {
//        if (!loadEnable) {
//            return;
//        }
//        try {
//            if (scrollState == OnScrollListener.SCROLL_STATE_IDLE
//                    && !isLoading
//                    && view.getLastVisiblePosition() == view
//                    .getPositionForView(footer) && !isLoadFull) {
//                onLoad();
//                isLoading = true;
//            }
//        } catch (Exception e) {
//        }
    }

    // 解读手势，刷新header状态
    private void whenMove(MotionEvent ev) {
        if (!isRecorded) {
            return;
        }
        int tmpY = (int) ev.getY();
        int space = tmpY - startY;
        int topPadding = space - headerContentHeight;
        switch (state) {
            case NONE:
                if (space > 0) {
                    state = PULL;
                    refreshHeaderViewByState();
                }
                break;
            case PULL:
                topPadding(topPadding);
                if (scrollState == SCROLL_STATE_TOUCH_SCROLL
                        && space > headerContentHeight + SPACE) {
                    state = RELEASE;
                    refreshHeaderViewByState();
                }
                break;
            case RELEASE:
                topPadding(topPadding);
                if (space > 0 && space < headerContentHeight + SPACE) {
                    state = PULL;
                    refreshHeaderViewByState();
                } else if (space <= 0) {
                    state = NONE;
                    refreshHeaderViewByState();
                }
                break;
        }

    }

    // 调整header的大小。其实调整的只是距离顶部的高度。
    private void topPadding(int topPadding) {
        header.setPadding(header.getPaddingLeft(), topPadding,
                header.getPaddingRight(), header.getPaddingBottom());
        header.invalidate();
    }

    /**
     * 这个方法是根据结果的大小来决定footer显示的。
     * <p>
     * 这里假定每次请求的条数为10。如果请求到了10条。则认为还有数据。如过结果不足10条，则认为数据已经全部加载，这时footer显示已经全部加载
     * </p>
     *
     * @param resultSize
     */
    public void setResultSize(int resultSize) {
        if (resultSize == 0) {
            isLoadFull = true;
            loadFull.setVisibility(View.GONE);
            loading.setVisibility(View.GONE);
            more.setVisibility(View.GONE);
            noData.setVisibility(View.VISIBLE);
        } else if (resultSize > 0 && resultSize < pageSize) {
            isLoadFull = true;
            loadFull.setVisibility(View.GONE);
            loading.setVisibility(View.GONE);
            more.setVisibility(View.GONE);
            noData.setVisibility(View.GONE);
        } else if (resultSize == pageSize) {
            isLoadFull = false;
            loadFull.setVisibility(View.GONE);
            loading.setVisibility(View.VISIBLE);
            more.setVisibility(View.VISIBLE);
            noData.setVisibility(View.GONE);
        } else if (resultSize > pageSize) {
            isLoadFull = false;
            loadFull.setVisibility(View.GONE);
            loading.setVisibility(View.GONE);
            more.setVisibility(View.GONE);
            noData.setVisibility(View.GONE);
        }

    }

    // 根据当前状态，调整header
    private void refreshHeaderViewByState() {
        switch (state) {

            case NONE:
                topPadding(-headerContentHeight);
                tip.setText(R.string.pull_to_refresh);
                refreshing.setVisibility(View.GONE);
                arrow.clearAnimation();
                arrow.setImageResource(R.drawable.default_ptr_rotate);
                break;
            case PULL:
                arrow.setVisibility(View.GONE);
                tip.setVisibility(View.VISIBLE);
                lastUpdate.setVisibility(View.VISIBLE);
                refreshing.setVisibility(View.GONE);
                tip.setText(R.string.pull_to_refresh);
                arrow.clearAnimation();
                arrow.setAnimation(reverseAnimation);
                break;
            case RELEASE:
                arrow.setVisibility(View.GONE);
                tip.setVisibility(View.VISIBLE);
                lastUpdate.setVisibility(View.VISIBLE);
                refreshing.setVisibility(View.GONE);
                tip.setText(R.string.pull_to_refresh);
                tip.setText(R.string.release_to_refresh);
                arrow.clearAnimation();
                arrow.setAnimation(animation);
                break;
            case REFRESHING:
                topPadding(headerContentInitialHeight);
                refreshing.setVisibility(View.VISIBLE);
                arrow.clearAnimation();
                arrow.setVisibility(View.GONE);
                tip.setVisibility(View.VISIBLE);
                tip.setText(R.string.more);
                lastUpdate.setVisibility(View.VISIBLE);
                break;
        }
    }

    // 用来计算header大小的。比较隐晦。因为header的初始高度就是0,貌似可以不用。
    private void measureView(View child) {
        ViewGroup.LayoutParams p = child.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,
                    MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = MeasureSpec.makeMeasureSpec(0,
                    MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec, childHeightSpec);
    }

    /*
     * 定义下拉刷新接口
     */
    public interface OnRefreshListener {
        public void onRefresh();
    }

    /*
     * 定义加载更多接口
     */
    public interface OnLoadListener {
        public void onLoad();
    }
}
