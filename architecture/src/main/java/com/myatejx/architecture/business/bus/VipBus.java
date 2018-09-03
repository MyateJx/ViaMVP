package com.myatejx.architecture.business.bus;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author KunMinX
 * @date 2018/8/22
 */
public class VipBus {

    private static HashMap<String, IRequest> sRequestMap = new HashMap<>();
    private static HashMap<String, List<IResponse>> sResponseMap = new HashMap<>();

    public static void registerRequestHandle(IRequest request) {
        if (request != null && TextUtils.isEmpty(request.getBusinessType()) && sRequestMap.get(request.getBusinessType()) == null) {
            sRequestMap.put(request.getBusinessType(), request);
        }
    }

    public static void unregisterRequestHandle(String requestType) {
        if (requestType != null && sRequestMap.get(requestType) != null) {
            sRequestMap.remove(requestType);
        }
    }

    public static void registerResponseObserve(String requestType, IResponse response) {
        if (response != null && requestType != null) {
            if (sResponseMap.get(requestType) == null) {
                List<IResponse> responses = new ArrayList<>();
                responses.add(response);
                sResponseMap.put(requestType, responses);
            } else {
                sResponseMap.get(requestType).add(response);
            }
        }
    }

    public static void unregisterResponseObserve(String requestType, IResponse response) {
        if (response != null && requestType != null && sResponseMap.get(requestType) != null) {
            if (sResponseMap.get(requestType).contains(response)) {
                sResponseMap.get(requestType).remove(response);
            }
        }
    }

    public static void clearAllRegister() {
        sRequestMap.clear();
        sResponseMap.clear();
    }

    public static IRequest request(String requestType) {
        return sRequestMap.get(requestType);
    }

    public static void response(String requestType, Result result) {
        List<IResponse> responseList = sResponseMap.get(requestType);
        if (responseList != null && responseList.size() > 0) {
            for (IResponse response : responseList) {
                response.onResult(result);
            }
        }
    }

}