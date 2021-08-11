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

package com.github.kshitijjain.instalike.utils;

import ohos.app.Context;
import ohos.global.resource.NotExistException;
import ohos.global.resource.ResourceManager;
import ohos.global.resource.WrongTypeException;
import java.io.IOException;

/**
 * Util class to fetch resource values from xml.
 */
public class ResourceUtil {

    private ResourceUtil() {
    }

    /**
     * Util method to access method.
     *
     * @param context - context
     * @param tag - tag to be displayed if exception occurs
     * @param resourceId - resource id
     * @return float value
     */
    public static float getFloat(Context context, String tag, int resourceId) {
        float result = 0;
        if (context == null) {
            LogUtil.error(tag, "getFloat -> get null context");
            return result;
        }
        ResourceManager manager = context.getResourceManager();
        if (manager == null) {
            LogUtil.error(tag, "getFloat -> get null ResourceManager");
            return result;
        }
        try {
            result = manager.getElement(resourceId).getFloat();
        } catch (IOException e) {
            LogUtil.error(tag, "getFloat -> IOException");
        } catch (NotExistException e) {
            LogUtil.error(tag, "getFloat -> NotExistException");
        } catch (WrongTypeException e) {
            LogUtil.error(tag, "getFloat -> WrongTypeException");
        }
        return result;
    }
}
