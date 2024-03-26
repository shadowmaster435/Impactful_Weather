package org.shadowmaster435.biomeparticleweather.gui;

import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import org.shadowmaster435.biomeparticleweather.util.ConfigFile;

import java.util.ArrayList;
import java.util.HashMap;

public class PagedScreen extends Screen {


    private final Screen parent;

    public int page = 0;


    public final ButtonWidget previous_button;
    public final ButtonWidget next_button;


    public HashMap<Integer, ArrayList<Object[]>> paged_elements = new HashMap<>();


    public PagedScreen(Screen parent) {
        super(Text.empty());
        this.parent = parent;
        paged_elements.put(0, new ArrayList<>());
        this.next_button = ButtonWidget.builder(Text.of(">"), (a) -> previous_page()).dimensions(parent.width - 20, parent.height - 20, 16, 16).build();
        this.previous_button = ButtonWidget.builder(Text.of("<"), (a) -> next_page()).dimensions(4, parent.height - 20, 16, 16).build();
        addDrawableChild(previous_button);
        addDrawableChild(next_button);
    }

    @Override
    public void close() {
        client.setScreen(parent);
    }
    public void set_page(int page) {
        this.page = MathHelper.clamp(page, 0, paged_elements.size() - 1);

        update_elements();
    }
    public void previous_page() {
        set_page(page + 1);
    }
    public void next_page() {
        set_page(page - 1);
    }

    public void add_entry(Object... widgets) {
        if (will_overflow(paged_elements.size() - 1)) {
            ArrayList<Object[]> new_list = new ArrayList<>();
            new_list.add(widgets);
            paged_elements.put(paged_elements.size(), new_list);
        } else {
            paged_elements.get(paged_elements.size() - 1).add(widgets);
        }
        update_elements();

    }

    public boolean will_overflow(int page) {
        return paged_elements.get(page).size() >= 10;
    }


    public void update_elements() {
        next_button.visible = this.page <= 0;
        next_button.active = this.page <= 0;
        previous_button.visible = this.page >= paged_elements.size() - 1;
        previous_button.active = this.page >= paged_elements.size() - 1;
        for (int i = 0; i < paged_elements.size(); ++i) {
            var page_elements = paged_elements.get(i);
            for (Object[] entry_elements : page_elements) {
                for (Object entry_element : entry_elements) {
                    var widget = (ClickableWidget) (entry_element);
                    var bool = i == page;
                    widget.visible = bool;
                }
            }
        }
    }



}
