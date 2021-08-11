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

package com.github.kshitijjain.instalikesample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;
import ohos.agp.components.Attr;
import ohos.agp.components.AttrSet;
import ohos.agp.components.Component;
import ohos.agp.components.Image;
import ohos.agp.components.element.Element;
import ohos.agp.components.element.ElementScatter;
import ohos.agp.render.ColorMatrix;
import ohos.app.Context;
import com.github.kshitijjain.instalike.InstaLikeView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Optional;

public class InstaLikeViewTest {
    private Context context;
    private AttrSet attrSet;
    private InstaLikeView instaLikeView;

    @Before
    public void setUp() {
        context = AbilityDelegatorRegistry.getAbilityDelegator().getAppContext();
        attrSet = new AttrSet() {
            @Override
            public Optional<String> getStyle() {
                return Optional.empty();
            }

            @Override
            public int getLength() {
                return 0;
            }

            @Override
            public Optional<Attr> getAttr(int i) {
                return Optional.empty();
            }

            @Override
            public Optional<Attr> getAttr(String s) {
                return Optional.empty();
            }
        };

    }

    @Test
    public void testImageHeart() {
        instaLikeView = new InstaLikeView(context, attrSet);
        assertNotNull(instaLikeView.getImageHeart());
    }

    @Test
    public void testImageHeartLayoutConfig() {
        instaLikeView = new InstaLikeView(context, attrSet);
        assertEquals(160, instaLikeView.getImageHeart().getLayoutConfig().width);
        assertEquals(160, instaLikeView.getImageHeart().getLayoutConfig().height);
    }

    @Test
    public void testImageHeartSrc() {
        instaLikeView = new InstaLikeView(context, attrSet);
        assertNotNull(instaLikeView.getImageHeart().getImageElement());
    }

    @Test
    public void testImageHeartScaleMode() {
        instaLikeView = new InstaLikeView(context, attrSet);
        assertEquals(Image.ScaleMode.STRETCH, instaLikeView.getImageHeart().getScaleMode());
    }

    @Test
    public void testImageHeartVisibility() {
        instaLikeView = new InstaLikeView(context, attrSet);
        assertEquals(Component.HIDE, instaLikeView.getImageHeart().getVisibility());
    }

    @Test
    public void testSetLikeColor() {
        instaLikeView = new InstaLikeView(context, attrSet);
        ColorMatrix colorMatrix = new ColorMatrix();
        float[] colorTransform = {
                0, 1f, 0, 0, 0, 0, 0, 0f, 0, 0, 0, 0, 0, 0f, 0, 0, 0, 0, 1f, 0
        };
        colorMatrix.setSaturation(0);
        colorMatrix.setMatrix(colorTransform);
        instaLikeView.setLikeColor(colorMatrix);
        ColorMatrix colorMatrix1 = instaLikeView.getImageHeart().getImageElement().getColorMatrix();
        assertEquals(colorMatrix1, colorMatrix);
    }

    @Test
    public void testSetLikeDrawable() {
        instaLikeView = new InstaLikeView(context, attrSet);
        Element element = ElementScatter.getInstance(context).parse(ResourceTable.Graphic_button_shape);
        instaLikeView.setLikeDrawable(element);
        assertNotNull(instaLikeView.getImageHeart().getImageElement());
    }

    @Test
    public void testSetLikeResource() {
        instaLikeView = new InstaLikeView(context, attrSet);
        instaLikeView.setLikeResource(ResourceTable.Media_chichen_itza);
        assertNotNull(instaLikeView.getImageHeart().getPixelMap());
    }

    @Test
    public void testStart() {
        instaLikeView = new InstaLikeView(context, attrSet);
        instaLikeView.start();
        assertTrue(instaLikeView.getImageHeart().getVisibility() == Component.VISIBLE);
    }

}