/*
 * Copyright (C) 2020-21 Application Library Engineering Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.kshitijjain.instalikesample.slice;

import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.RadioButton;
import ohos.agp.components.element.ElementScatter;
import ohos.agp.render.ColorMatrix;
import ohos.agp.window.dialog.ToastDialog;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import com.github.kshitijjain.instalike.InstaLikeView;
import com.github.kshitijjain.instalikesample.ResourceTable;

/**
 * Sample app to test the InstaLike library functionality.
 */
public class MainAbilitySlice extends AbilitySlice {
    private static final String TAG = "MainAbilitySlice";
    private static final int ZERO = 0;
    private static final HiLogLabel HILOG_LABEL = new HiLogLabel(ZERO, ZERO, TAG);
    private static final int COLOR_RED = 0;
    private static final int COLOR_GREEN = 1;
    private static final int COLOR_BLUE = 2;
    private InstaLikeView instaLikeView;
    private DirectionalLayout colorLayout;
    private RadioButton imageRadio;
    private RadioButton vectorRadio;
    private RadioButton colorRed;
    private RadioButton colorGreen;
    private RadioButton colorBlue;
    private Button animate;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        HiLog.debug(HILOG_LABEL, "onStart");
        setUIContent(ResourceTable.Layout_ability_main);
        initViews();
        setListeners();
    }

    private void initViews() {
        instaLikeView = (InstaLikeView) findComponentById(ResourceTable.Id_instalikeview);
        imageRadio = (RadioButton) findComponentById(ResourceTable.Id_radio_image);
        vectorRadio = (RadioButton) findComponentById(ResourceTable.Id_radio_vector);
        colorLayout = (DirectionalLayout) findComponentById(ResourceTable.Id_color_layout);
        colorRed = (RadioButton) findComponentById(ResourceTable.Id_color_red);
        colorGreen = (RadioButton) findComponentById(ResourceTable.Id_color_green);
        colorBlue = (RadioButton) findComponentById(ResourceTable.Id_color_blue);
        animate = (Button) findComponentById(ResourceTable.Id_animate_button);
        vectorRadio.setChecked(true);
        colorRed.setChecked(true);
        ColorMatrix colorMatrix = createColorMatrix(COLOR_RED);
        instaLikeView.setLikeColor(colorMatrix);
        colorLayout.setVisibility(Component.VISIBLE);
    }

    private void setListeners() {
        imageRadio.setCheckedStateChangedListener(((absButton, isChecked) -> {
            if (!isChecked) {
                return;
            }
            instaLikeView.setLikeResource(ResourceTable.Media_icon);
            colorLayout.setVisibility(Component.HIDE);
            showToast(getString(ResourceTable.String_image_toast));
        }));
        vectorRadio.setCheckedStateChangedListener(((absButton, isChecked) -> {
            if (!isChecked) {
                return;
            }
            instaLikeView.setLikeDrawable(ElementScatter.getInstance(MainAbilitySlice.this)
                    .parse(ResourceTable.Graphic_img_heart));
            colorLayout.setVisibility(Component.VISIBLE);
            showToast(getString(ResourceTable.String_vector_toast));
            colorRed.setChecked(true);
            ColorMatrix colorMatrix = createColorMatrix(COLOR_RED);
            instaLikeView.setLikeColor(colorMatrix);
        }));
        colorRed.setCheckedStateChangedListener((absButton, isChecked) -> {
            if (!isChecked) {
                return;
            }
            ColorMatrix colorMatrix = createColorMatrix(COLOR_RED);
            instaLikeView.setLikeColor(colorMatrix);
            showToast(getString(ResourceTable.String_set_red));
        });
        colorGreen.setCheckedStateChangedListener((absButton, isChecked) -> {
            if (!isChecked) {
                return;
            }
            ColorMatrix colorMatrix = createColorMatrix(COLOR_GREEN);
            instaLikeView.setLikeColor(colorMatrix);
            showToast(getString(ResourceTable.String_set_green));
        });
        colorBlue.setCheckedStateChangedListener((absButton, isChecked) -> {
            if (!isChecked) {
                return;
            }
            ColorMatrix colorMatrix = createColorMatrix(COLOR_BLUE);
            instaLikeView.setLikeColor(colorMatrix);
            showToast(getString(ResourceTable.String_set_blue));
        });
        animate.setClickedListener(component -> instaLikeView.start());
    }

    private void showToast(String message) {
        ToastDialog toastDialog = new ToastDialog(getContext());
        toastDialog.setText(message).show();
    }

    private ColorMatrix createColorMatrix(int colorCode) {
        ColorMatrix colorMatrix = new ColorMatrix();
        float[] colorTransform = new float[20];
        switch (colorCode) {
            case COLOR_RED:
                colorTransform = new float[]{
                    0, 1f, 0, 0, 0,
                    0, 0, 0f, 0, 0,
                    0, 0, 0, 0f, 0,
                    0, 0, 0, 1f, 0
                };
                break;
            case COLOR_GREEN:
                colorTransform = new float[]{
                    0, 0f, 0, 0, 0,
                    0, 0, 1f, 0, 0,
                    0, 0, 0, 0f, 0,
                    0, 0, 0, 1f, 0
                };
                break;
            case COLOR_BLUE:
                colorTransform = new float[]{
                    0, 0f, 0, 0, 0,
                    0, 0, 0f, 0, 0,
                    0, 0, 0, 1f, 0,
                    0, 0, 0, 1f, 0
                };
                break;
            default:
                break;
        }

        colorMatrix.setSaturation(ZERO);
        colorMatrix.setMatrix(colorTransform);
        return colorMatrix;
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
