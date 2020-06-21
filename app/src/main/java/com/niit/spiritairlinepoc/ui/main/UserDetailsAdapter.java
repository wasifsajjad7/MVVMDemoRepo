package com.niit.spiritairlinepoc.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.niit.spiritairlinepoc.R;
import com.niit.spiritairlinepoc.data.network.model.UserDetails;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserDetailsAdapter extends RecyclerView.Adapter<UserDetailsAdapter.UserDetailsViewHolder> {

    public interface UserDetailsListener {
        void onUserDetailsClicked(UserDetails userDetails);
    }

    private List<UserDetails> items;
    private UserDetailsListener listener;

    public UserDetailsAdapter(UserDetailsListener listener) {
        this.listener = listener;
        items = new ArrayList<>();
    }

    public void setItems(List<UserDetails> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public UserDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_details, parent, false);
        return new UserDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserDetailsViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private UserDetails getItem(int position) {
        return items.get(position);
    }

    public class UserDetailsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.image) AppCompatImageView image;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.desc)
        TextView desc;

        UserDetailsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(int position) {
            UserDetails userDetails = getItem(position);

            setClickListener(userDetails);
            setTitle(userDetails.getFirstName());
            setImage(userDetails.getAvatar());
            setDescription(userDetails.getEmail());
        }

        private void setTitle(String title) {
            this.title.setText(title);
        }

        private void setImage(String imageUrl) {
            Glide.with(itemView.getContext()).load(imageUrl).into(image);
        }

        private void setDescription(String description) {
            desc.setText(description);
        }

        private void setClickListener(UserDetails userDetails) {
            itemView.setTag(userDetails);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onUserDetailsClicked((UserDetails) view.getTag());
        }
    }
}