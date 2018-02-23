package rick.trainset.Presentation.HomeActivity;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rick.trainset.R;

/**
 * Created by Rick on 2/18/2018.
 */


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    ArrayList<String> categoryList;

    public CategoryAdapter(ArrayList<String> categoryList) {

        this.categoryList = categoryList;
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
         String category = categoryList.get(position);

         holder.tvCategory.setText(category);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.category)
        TextView tvCategory;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

    private void remove(int position) {

        //TODO database method here
        this.categoryList.remove(position);
        notifyItemRemoved(position);
    }
}

