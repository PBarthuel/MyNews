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
import com.example.mynews.model.TopStoryArticle;

import java.util.List;

public class TopStoryArticleAdapter extends RecyclerView.Adapter<TopStoryArticleAdapter.ViewHolder> {

    private List<TopStoryArticle> mList;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.main_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int index) {

        TopStoryArticle topStoryArticle = mList.get(index);

        viewHolder.mTextViewTitle.setText(topStoryArticle.getTitle());
        if (topStoryArticle.getMultimedia().size() > 0) {
            @Px int pixelSize = viewHolder.mImageViewThumbnail.getContext().getResources().getDimensionPixelSize(R.dimen.image_size);
            Glide.with(viewHolder.mImageViewThumbnail).load(topStoryArticle.getMultimedia().get(0).getUrl()).into(viewHolder.mImageViewThumbnail);
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void setNewData(List<TopStoryArticle> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTextViewTitle;
        private final ImageView mImageViewThumbnail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewTitle = itemView.findViewById(R.id.main_item_tv_title);
            mImageViewThumbnail = itemView.findViewById(R.id.main_item_iv_thumbnail);
        }
    }
}

