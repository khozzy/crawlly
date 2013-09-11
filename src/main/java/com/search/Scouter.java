package com.search;

import com.common.entity.Page;

/**
 * Klasa odpowiedzialna za przeszukiwanie strony pod kątem interesujących wyrażeń
 */
public class Scouter {
    private Page page;

    public Scouter(Page page) {
        this.page = page;
    }

    /**
     * Zwraca TRUE jeśli strona zawiera interesujące frazy
     */
    public Boolean isInteresting() {
        // ...
        return null;
    }
}
