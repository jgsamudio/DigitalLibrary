// AUTOGENERATED FILE - DO NOT MODIFY!
// This file generated by Djinni from shared-library.djinni

#include "MainViewModel.hpp"
#include <memory>

static_assert(__has_feature(objc_arc), "Djinni requires ARC to be enabled for this file");

@class MainViewModel;

namespace djinni_generated {

class MainViewModel
{
public:
    using CppType = std::shared_ptr<::shared-library::MainViewModel>;
    using CppOptType = std::shared_ptr<::shared-library::MainViewModel>;
    using ObjcType = MainViewModel*;

    using Boxed = MainViewModel;

    static CppType toCpp(ObjcType objc);
    static ObjcType fromCppOpt(const CppOptType& cpp);
    static ObjcType fromCpp(const CppType& cpp) { return fromCppOpt(cpp); }

private:
    class ObjcProxy;
};

}  // namespace djinni_generated

