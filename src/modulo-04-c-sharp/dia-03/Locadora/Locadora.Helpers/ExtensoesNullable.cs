using System.ComponentModel;

namespace System
{
    public static class ExtensoesNullable
    {
        public static Nullable<T> ToNullable<T>(this string s) where T : struct
        {
            Nullable<T> result = new Nullable<T>();

            if (!string.IsNullOrEmpty(s) && s.Trim().Length > 0)
            {
                TypeConverter conv = TypeDescriptor.GetConverter(typeof(T));
                result = (T)conv.ConvertFrom(s);
            }

            return result;
        }
    }
}
