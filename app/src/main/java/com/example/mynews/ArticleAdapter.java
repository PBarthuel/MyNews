package com.example.mynews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mynews.ui.Article;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private List<Article> mList;
    private Listener listener;

    public ArticleAdapter(Listener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.main_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int index) {

        viewHolder.bind(mList.get(index), listener);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void setNewData(List<Article> list) {
        mList = list;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mImageViewThumbnail;
        private final TextView mTextViewTopic;
        private final TextView mTextViewDate;
        private final TextView mTextViewTitle;
        private final TextView mTextViewUrl;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextViewUrl = itemView.findViewById(R.id.main_item_tv_url);
            mTextViewTopic = itemView.findViewById(R.id.main_item_tv_topic);
            mTextViewDate = itemView.findViewById(R.id.main_item_tv_date);
            mTextViewTitle = itemView.findViewById(R.id.main_item_tv_title);
            mImageViewThumbnail = itemView.findViewById(R.id.main_item_iv_thumbnail);
        }

        private void bind(final Article article, final Listener listener) {

            mTextViewUrl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onArticleClick(article);
                }
            });
            mTextViewTopic.setText(article.getTopic());
            mTextViewDate.setText(article.getDate());
            mTextViewTitle.setText(article.getTitle());
            Glide.with(mImageViewThumbnail).load(article.getImageUrl()).into(mImageViewThumbnail);
        }
    }

    public interface Listener {
         void onArticleClick(Article article);
    }
}

