package com.mgzxc.fastec;

import com.mgzxc.latte_core.activities.ProxyActivity;
import com.mgzxc.latte_core.delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity {


    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
