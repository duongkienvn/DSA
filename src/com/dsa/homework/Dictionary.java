package com.dsa.homework;


import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class Dictionary {
    private DictList<String> enList = null; // Danh sách lưu các từ tiếng Anh
    private DictList<String> viList = null; // Danh sách lưu các từ tiếng Việt
    private Map<Integer, String> viMap = null;
    private Map<String, Integer> enMap = null;

    //	Phương thức loadDictionary(String[] en, String[] vi), phương thức này thực hiện nạp từ điển,
    //lưu các phần tử trong mảng en vào danh sách chứa các từ tiếng Anh,
    //và các phần từ trong mảng vi vào danh sách chứa các từ tiếng Việt.
    public void loadDictionary(String[] en, String[] vi) {
        enList = new DictList<>();
        viList = new DictList<>();
        viMap = new HashMap<>();
        enMap = new HashMap<>();

        for (int i = 0; i < en.length; i++) {
            enMap.put(en[i], i);
            enList.add(en[i]);
        }

        for (int i = 0; i < vi.length; i++) {
            viMap.put(i, vi[i]);
            viList.add(vi[i]);
        }
    }

    //	Phương thức translate(DictList en),
    //phương thức này trả lại 1 danh sách các từ tiếng Việt tương ứng với các từ trong danh sách en.

    public DictList<String> translate(DictList<String> en) {
        DictList<String> result = new DictList<>();
        for (int i = 0; i < en.size(); i++) {
            result.add(viMap.get(enMap.get(en.get(i))));
        }

        return result;
    }

}


