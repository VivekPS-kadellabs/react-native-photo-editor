package com.ahmedadeltito.photoeditor;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import ui.photoeditor.R;

/**
 * Created by Ahmed Adel on 5/5/17.
 */

public class EmojiFragment extends Fragment implements EmojiAdapter.OnEmojiClickListener, EmojiAdapter.OnColorClickListener {

    private ArrayList<String> emojiIds;
    private PhotoEditorActivity photoEditorActivity;
    RecyclerView emojiRecyclerView;
    RecyclerView addEmojiColorPickerRecyclerView;
    private ArrayList<Integer> colorPickerColors;
    private int emojiColorCode = -1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        photoEditorActivity = (PhotoEditorActivity) getActivity();

        String[] emojis = photoEditorActivity.getResources().getStringArray(R.array.photo_editor_emoji);

        emojiIds = new ArrayList<>();
        Collections.addAll(emojiIds, emojis);

        colorPickerColors = new ArrayList<>();
            colorPickerColors.add(getResources().getColor(R.color.black));
            colorPickerColors.add(getResources().getColor(R.color.blue_color_picker));
            colorPickerColors.add(getResources().getColor(R.color.brown_color_picker));
            colorPickerColors.add(getResources().getColor(R.color.green_color_picker));
            colorPickerColors.add(getResources().getColor(R.color.orange_color_picker));
            colorPickerColors.add(getResources().getColor(R.color.red_color_picker));
            colorPickerColors.add(getResources().getColor(R.color.red_orange_color_picker));
            colorPickerColors.add(getResources().getColor(R.color.sky_blue_color_picker));
            colorPickerColors.add(getResources().getColor(R.color.violet_color_picker));
            colorPickerColors.add(getResources().getColor(R.color.white));
            colorPickerColors.add(getResources().getColor(R.color.yellow_color_picker));
            colorPickerColors.add(getResources().getColor(R.color.yellow_green_color_picker));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_photo_edit_emoji, container, false);

        emojiRecyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_main_photo_edit_emoji_rv);
        addEmojiColorPickerRecyclerView = (RecyclerView) rootView.findViewById(R.id.add_emoji_color_picker_recycler_view);
        addEmojiColorPickerRecyclerView.setLayoutManager(new LinearLayoutManager(photoEditorActivity, LinearLayoutManager.HORIZONTAL, false));
        addEmojiColorPickerRecyclerView.setHasFixedSize(true);

        ColorPickerAdapter colorPickerAdapter = new ColorPickerAdapter(photoEditorActivity, colorPickerColors);
        colorPickerAdapter.setOnColorPickerClickListener(new ColorPickerAdapter.OnColorPickerClickListener() {
            @Override
            public void onColorPickerClickListener(int colorCode) {
                System.out.println("=================== colorCode "+colorCode);
                emojiColorCode=colorCode;
                EmojiAdapter adapter = new EmojiAdapter(photoEditorActivity, emojiIds,emojiColorCode);
                adapter.setOnColorClickListener(colorCode);
                adapter.setOnEmojiClickListener(EmojiFragment.this::onEmojiClickListener);
                emojiRecyclerView.setAdapter(adapter);
            }
        });
        addEmojiColorPickerRecyclerView.setAdapter(colorPickerAdapter);

        emojiRecyclerView.setHasFixedSize(true);
        emojiRecyclerView.setLayoutManager(new GridLayoutManager(photoEditorActivity, 4));
        EmojiAdapter adapter = new EmojiAdapter(photoEditorActivity, emojiIds,emojiColorCode);
        adapter.setOnEmojiClickListener(this);
        emojiRecyclerView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onEmojiClickListener(String emojiName) {
        System.out.println("================ emojiColorCode "+emojiColorCode);
        photoEditorActivity.addEmoji(emojiName,emojiColorCode);
    }

    @Override
    public void onColorClickListener(int colorName) {
        System.out.println("================123 emojiColorCode "+emojiColorCode);
    }
}