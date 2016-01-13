package guLang.recycleView;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.guLang.recycleView.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private Context context;
    private List<ImageItem> list;
    private Resources res;

    public RecyclerAdapter(Context context, List<ImageItem> list) {
        this.context = context;
        this.list = list;
        res = context.getResources();
    }

    /**
     * 填充布局
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card_view, parent, false);
        return new ViewHolder(view);
    }

    /**
     * 绑定数据
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ImageItem bean = list.get(position);
        holder.title.setText(bean.getTitle());
        holder.imageView.setImageResource(bean.getResId());
        Bitmap bitmap = BitmapFactory.decodeResource(res, bean.getResId());
        //异步获得bitmap图片颜色值
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrant = palette.getVibrantSwatch();//有活力
                Palette.Swatch c = palette.getDarkVibrantSwatch();//有活力 暗色
                Palette.Swatch d = palette.getLightVibrantSwatch();//有活力 亮色
                Palette.Swatch f = palette.getMutedSwatch();//柔和
                Palette.Swatch a = palette.getDarkMutedSwatch();//柔和 暗色
                Palette.Swatch b = palette.getLightMutedSwatch();//柔和 亮色

                if (vibrant != null) {
                    holder.title.setBackgroundColor(
                            vibrant.getRgb());
                    holder.title.setTextColor(
                            vibrant.getTitleTextColor());
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, bean.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return null == list ? 0 : list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView title;

        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.pic);
            title = (TextView) view.findViewById(R.id.name);
        }
    }
}
