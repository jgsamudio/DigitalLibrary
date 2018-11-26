// AUTOGENERATED FILE - DO NOT MODIFY!
// This file generated by Djinni from shared-library.djinni

package com.example.prolificinteractive;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class MainViewModel {
    public abstract void loadLibrary();

    private static final class CppProxy extends MainViewModel
    {
        private final long nativeRef;
        private final AtomicBoolean destroyed = new AtomicBoolean(false);

        private CppProxy(long nativeRef)
        {
            if (nativeRef == 0) throw new RuntimeException("nativeRef is zero");
            this.nativeRef = nativeRef;
        }

        private native void nativeDestroy(long nativeRef);
        public void _djinni_private_destroy()
        {
            boolean destroyed = this.destroyed.getAndSet(true);
            if (!destroyed) nativeDestroy(this.nativeRef);
        }
        protected void finalize() throws java.lang.Throwable
        {
            _djinni_private_destroy();
            super.finalize();
        }

        @Override
        public void loadLibrary()
        {
            assert !this.destroyed.get() : "trying to use a destroyed object";
            native_loadLibrary(this.nativeRef);
        }
        private native void native_loadLibrary(long _nativeRef);
    }
}
