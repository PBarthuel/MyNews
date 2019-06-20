package com.example.mynews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Px;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private List<Result> mList;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.main_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int index) {
        Result result = mList.get(index);
        viewHolder.mTextViewTitle.setText(result.getTitle());
        viewHolder.mTextViewSection.setText(result.getSection());
        if (result.getMultimedia().size() > 0) {
            @Px int pixelSize = viewHolder.mImageViewThumbnail.getContext().getResources().getDimensionPixelSize(R.dimen.image_size);
            Glide.with(viewHolder.mImageViewThumbnail).load(result.getMultimedia().get(0).getUrl()).into(viewHolder.mImageViewThumbnail);
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void setNewData(List<Result> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTextViewTitle;
        private final TextView mTextViewSection;
        private final ImageView mImageViewThumbnail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewTitle = itemView.findViewById(R.id.main_item_tv_title);
            mTextViewSection = itemView.findViewById(R.id.main_item_tv_Section);
            mImageViewThumbnail = itemView.findViewById(R.id.main_item_iv_thumbnail);
        }
    }
}

