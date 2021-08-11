package com.github.kshitijjain.instalike;

import static ohos.agp.components.DependentLayout.LayoutConfig.CENTER_IN_PARENT;
import ohos.agp.animation.Animator;
import ohos.agp.animation.AnimatorProperty;
import ohos.agp.components.AttrSet;
import ohos.agp.components.DependentLayout;
import ohos.agp.components.Image;
import ohos.agp.components.element.Element;
import ohos.agp.components.element.ElementScatter;
import ohos.agp.render.ColorMatrix;
import ohos.app.Context;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by flash on 17/3/17.
 */
public class InstaLikeView extends DependentLayout {
    private static final String LIKE_SRC = "likeSrc";
    private static final String LIKE_SIZE = "likeSize";
    private static final int START_ANIM_DURATION = 400;
    private static final int END_ANIM_DURATION = 100;
    private static final int TOTAL_DURATION = 800;
    private static final float FLOAT_ZERO = 0f;
    private static final float FLOAT_ONE = 1f;
    private static final int DEFAULT_SIZE = 100;
    private Image mImageHeart;

    public InstaLikeView(Context context) {
        super(context);
        init(context, null);
    }

    public InstaLikeView(Context context, AttrSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public InstaLikeView(Context context, AttrSet attrs, String defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttrSet attrs) {
        mImageHeart = new Image(context);
        int likeSize = DEFAULT_SIZE;
        Element likeSrc = ElementScatter.getInstance(context).parse(ResourceTable.Graphic_img_heart);
        if (attrs != null) {
            boolean isLikeSizePresent = attrs.getAttr(LIKE_SIZE).isPresent();
            boolean isLikeSrcPresent = attrs.getAttr(LIKE_SRC).isPresent();
            if (isLikeSizePresent) {
                likeSize = attrs.getAttr(LIKE_SIZE).get().getDimensionValue();
            }
            if (isLikeSrcPresent) {
                likeSrc = attrs.getAttr(LIKE_SRC).get().getElement();
            }
        }
        DependentLayout.LayoutConfig layoutConfig = new DependentLayout.LayoutConfig(likeSize, likeSize);
        layoutConfig.addRule(CENTER_IN_PARENT);
        mImageHeart.setLayoutConfig(layoutConfig);
        mImageHeart.setScaleMode(Image.ScaleMode.STRETCH);
        mImageHeart.setVisibility(HIDE);
        mImageHeart.setImageElement(likeSrc);
        addComponent(mImageHeart);
    }

    /**
     * Triggers the animation of the component.
     */
    public void start() {
        mImageHeart.setVisibility(VISIBLE);
        animate();
    }

    private void animate() {
        AnimatorProperty animatorProperty = this.createAnimatorProperty()
                .setCurveType(Animator.CurveType.OVERSHOOT)
                .scaleXFrom(FLOAT_ZERO)
                .scaleX(FLOAT_ONE)
                .scaleYFrom(FLOAT_ZERO)
                .scaleY(FLOAT_ONE)
                .setDuration(START_ANIM_DURATION);
        animatorProperty.setStateChangedListener(new Animator.StateChangedListener() {
            @Override
            public void onStart(Animator animator) {
                // Need not implement
            }

            @Override
            public void onStop(Animator animator) {
                // Need not implement
            }

            @Override
            public void onCancel(Animator animator) {
                // Need not implement
            }

            @Override
            public void onEnd(Animator animator) {
                new Timer().schedule(
                        new TimerTask() {
                            @Override
                            public void run() {
                                getContext().getUITaskDispatcher().asyncDispatch(() -> {
                                    InstaLikeView.this.createAnimatorProperty()
                                            .scaleXFrom(FLOAT_ONE)
                                            .scaleX(FLOAT_ZERO)
                                            .scaleYFrom(FLOAT_ONE)
                                            .scaleY(FLOAT_ZERO)
                                            .setDuration(END_ANIM_DURATION)
                                            .start();
                                });
                            }
                        }, TOTAL_DURATION);
            }

            @Override
            public void onPause(Animator animator) {
                // Need not implement
            }

            @Override
            public void onResume(Animator animator) {
                // Need not implement
            }
        });
        animatorProperty.start();
    }

    /**
     * To access the image component.
     *
     * @return Image object
     */
    public Image getImageHeart() {
        return mImageHeart;
    }

    /**
     * This method must be used only when the color needs to be filled
     * within a vector element that is set within the view.
     * If imageElement within the view is null, then it has no effect
     *
     * @param colorMatrix - the colorMatrix that will be set for the image
     */
    public void setLikeColor(ColorMatrix colorMatrix) {
        if (mImageHeart.getImageElement() != null) {
            mImageHeart.getImageElement().setColorMatrix(colorMatrix);
        }
    }

    /**
     * This method can be used when a vector has to be set to the image component.
     *
     * @param imageDrawable - the element object that needs to be set to the image component
     */
    public void setLikeDrawable(Element imageDrawable) {
        mImageHeart.setImageElement(imageDrawable);
    }

    /**
     * This method needs to be called when a media resource needs to set to the image component.
     *
     * @param imageResource - the id of the media resource that needs to be set to image component
     */
    public void setLikeResource(int imageResource) {
        mImageHeart.setPixelMap(imageResource);
    }
}
